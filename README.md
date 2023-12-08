# quote_API -- REST API for 'Quotes' interface

This API provides tool to interact with ready interface

## Use cases

### Create user
```
POST /api/v1/user
```

### Create quote
```
POST /api/v1/quote/{id}
```

### Get 10 best
```
GET /api/v1/quote/best
```

### Get 10 worst
```
GET /api/v1/quote/worst
```

### Upvote
```
GET /api/v1/quote/up/{id}
```

### Downvote
```
GET /api/v1/quote/down/{id}
```
