# Testing

- Open any REST Client.
- Authenticate against the authentication endpoint localhost:8080/auth/login with this data (POST request):
- 
```
	{"email":"my@mail.com", "password":"my_pass"}"
```

- Header: Content-Type application/json
	+ Example with curl:

```
curl -v -H "Content-Type: application/json" -d "{\"email\":\"my@mail.com\", \"password\":\"my_pass\"}" localhost:8080/auth/login
```

- Response:

```
{
  "email": "my@mail.com",
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLG5hbUBjb2RlamF2YS5..."
}
```


- Then send a request to restricted endpoint using the token:
	+ Example with curl:

```
curl -v -H "Authorization: Bearer [accessToken]" localhost:8080/products
```

