# Evaluation Report: availability-eval

**Model:** nemotron-3-super

**Started:** 2026-07-26T21:32:31.289591437+00:00

**Completed:** 2026-07-26T21:59:20.472378201+00:00

## Summary

| Metric | Value |
|--------|-------|
| Total cases | 6 |
| Passed | 4 |
| Failed | 2 |
| Pass@1 | 67% |
| Pass after repair | 33% |

## Cases

| # | Name | Expected | Actual | Pass | Attempts | Time |
|---|------|----------|--------|------|----------|------|
| 1 | simple-greeting | completed | failed | ✗ | 2 | 38.9s |
| 2 | school-service-3obj | completed | completed | ✓ | 1 | 19.2s |
| 3 | context-overflow-reject | preflight_rejected | preflight_rejected | ✓ | 0 | 0.1s |
| 4 | moving-company-10obj | completed | completed | ✓ | 1 | 258.3s |
| 5 | moving-company-20obj | completed | failed | ✗ | 2 | 651.2s |
| 6 | moving-company-30obj | completed | completed | ✓ | 2 | 641.5s |

## Errors

### simple-greeting

```
Domain validation: 4 errors
```

### context-overflow-reject

```
Budget exceeded: estimated 92030 + completion 4096 + safety 8192 = 104318 > context 65536
```

### moving-company-20obj

```
Domain validation: 4 errors
```

