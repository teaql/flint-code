# TeaQL KSML Model Generation Rules

KSML is a compact XML modeling format used as the semantic source for TeaQL code
generation. These rules are optimized for coding agents that turn natural
language business descriptions into generated Java or Rust TeaQL projects.

## 10-Second XML Shape Check

| Case | Correct shape |
| --- | --- |
| Business object fields | Fields are XML attributes: `<school name="Example School" phone="13800138000"/>` |
| Constant object values | Values are `<_value>` child elements: `<school_type ...><_value id="1001" name="Primary" code="PRIMARY"/></school_type>` |
| Common mistake | Do not write business fields as child elements: `<school><name>Example School</name></school>` |

## Critical Rules

### Only Constant Objects Have `id="id()"`

Constant objects:
- Must have `id="id()"`.
- Must have `_constant="true"`.
- Must have `_identifier="code"`.
- Must have `<_value>` children.

Business objects:
- Must never have `id="id()"`.
- Must never have `_constant="true"`.
- Must never have `_identifier`.
- Must never have `<_value>` children.

### Constant Objects Must Reference the Domain Root

Every constant object MUST include a reference to the domain root object.
For example, if `bookstore` is the domain root:

```xml
<book_category _name="Book Category"
               _module="Core" _module_key="core"
               id="id()" name="string()" code="string()"
               _constant="true" _identifier="code"
               bookstore="bookstore()">
  <_value id="1001" name="Fiction" code="FICTION"/>
</book_category>
```

Without `bookstore="bookstore()"`, you will get error `KSML-CONSTANT-002`.

### Domain Root Object

Every model must have exactly one domain root business object — the largest
organizational boundary. All other business objects and constant objects must
reference it (directly or through a chain of references).

### Multi-File Layout with `<_include>`

For models with more than 10 objects, ALWAYS split into multiple files:

```xml
<!-- main.xml — entry point, contains ONLY root + includes -->
<?xml version="1.0" encoding="UTF-8"?>
<root alias_model_name="moving_company_service"
      chinese_name="搬家公司服务"
      english_name="Moving Company Service"
      name="moving-company-service"
      data_service="sqlite"
      org="doublechaintech"
      _module_key="root">

  <_include file="operations.xml" />
  <_include file="employees.xml" />
  <_include file="customers.xml" />
  <_include file="services.xml" />
  <_include file="finance.xml" />
  <_include file="assets.xml" />
  <_include file="administration.xml" />
  <_include file="platform.xml" />
</root>
```

```xml
<!-- operations.xml — one module per file -->
<moving_order _name="Moving Order"
              _module="Operations"
              _module_key="operations"
              order_number="MO-2024-001"
              scheduled_date="2024-03-15"
              origin_address="123 Main St"
              destination_address="456 Oak Ave"
              status="order_status()"
              customer="customer()"
              create_time="createTime()"
              update_time="updateTime()"/>

<order_status _name="Order Status"
              _module="Operations"
              _module_key="operations"
              id="id()" name="string()" code="string()"
              _constant="true" _identifier="code">
  <_value id="1001" name="Pending" code="PENDING"/>
  <_value id="1002" name="Confirmed" code="CONFIRMED"/>
  <_value id="1003" name="In Progress" code="IN_PROGRESS"/>
  <_value id="1004" name="Completed" code="COMPLETED"/>
  <_value id="1005" name="Cancelled" code="CANCELLED"/>
</order_status>
```

Each included file contains ONLY the object elements (no `<?xml?>` header, no `<root>` wrapper).

### Attribute Order

Use this order for business object attributes:
1. Identity attributes: `name`, `number`, `code`
2. Relationship references: `status`, `provider`, parent objects
3. System fields: `create_time`, `update_time`

### Naming Rules

- Object names: lowercase snake_case (e.g. `moving_order`, `employee`)
- Use complete domain words, not abbreviations (`account_number` not `acct_no`)
- NEVER use reserved keywords: `move`, `type`, `class`, `match`, `async`, `user`, `order`, `select`, `from`, `where`, `group`
- Use domain-qualified names instead: `moving_event`, `item_type`, `user_account`, `sort_order`

### Sensitive Fields

Any field containing personal/secret data MUST be listed in `_audit_mask_fields`:
```xml
<employee _name="Employee"
          _audit_mask_fields="password_hash,tax_id,bank_account"
          password_hash="secret123"
          tax_id="123-45-6789"
          .../>
```

## Modeling Checklist

Before delivering a KSML model, verify:

### Root Element
- [ ] Exactly one `<root>` element (not `<model>`, `<ksml>`, `<domain>`)
- [ ] `alias_model_name` is snake_case
- [ ] `name` is kebab-case ending in `-service`
- [ ] `data_service="sqlite"` and `org="doublechaintech"`
- [ ] `_module_key="root"`

### Structure
- [ ] All objects are direct children of `<root>` (or in included files)
- [ ] No nested objects except `<_value>` inside constants
- [ ] Object names are unique lowercase snake_case
- [ ] No reserved keywords used as object or attribute names

### Business Objects
- [ ] Each has `_name`, `_module`, `_module_key`
- [ ] None has `id="id()"` or `_constant="true"`
- [ ] None has `<_value>` children

### Constant Objects
- [ ] Each has `id="id()"`, `_constant="true"`, `_identifier="code"`
- [ ] Each has at least one `<_value>` child
- [ ] Uses `name="string()" code="string()"` (not literal values)
