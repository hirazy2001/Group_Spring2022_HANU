# **HANU BANKING SYSTEM**

<p align="center">
    <img src="https://www.paymentscardsandmobile.com/wp-content/uploads/2018/08/Future-of-banking.jpg"/>
</p>
<br>

# **API DOCUMENTATION**

Initialize Database MySQL:
```bash
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

If you choose to generate the authentication API, you can start to play with it.
> Note that creating and authenticating users needs a master key (which is defined in the `.env` file)

- Standard
    - Gender
        - true: MALE
        - false: FEMALE
    
    - Role
      - ROLE_USER: User
      - ROLE_MODERATOR: Moderator
      - ROLE_ADMIN: Admin
  
    - Account Type
      - PRIMARY: Primary Account
      - SAVING: Saving Account
      - LOAN: Loan Account

# **API User**

<span style="color:yellow">
 Create a user (sign up):
</span>

```bash
curl --location --request POST 'http://localhost:8080/users/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "hihi118",
    "password": "yo12346",
    "gender": true,
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "email": "1901040195@s.hanu.edu.vn",
    "dob": null,
    "phoneNumber": "0397286905",
    "address": "Hehe",
    "role": ["user"]
}'
```

It will return something like:
```bash
HTTP/1.1 201 Created
...
{
    "id": 1,
    "username": "hihi118",
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "gender": true,
    "phoneNumber": "0397286905",
    "picture": "https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp",
    "dob": "2022-04-05T10:35:30.6840429",
    "createdAt": "2022-04-05T10:35:30.6840429"
}
```

<span style="color:chartreuse">
 Get a user (get user):
</span>

```bash
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTMwNTM1LCJleHAiOjE2NDkyMTY5MzV9.DFo665vJsdi2E2OK_CoYknoAlEIcprfHrQ6vt4ZsqjpfpAedLm05ey9QZB5hkyhV7YmfsdqZK6kCwWZqdqMfKg'
```

It will return something like:

```bash
{
    "id": 1,
    "username": "hihi118",
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "gender": true,
    "phoneNumber": "0397286905",
    "picture": "https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp",
    "dob": "2022-04-05T10:35:30.6840429",
    "createdAt": "2022-04-05T10:36:07"
}
```

<span style="color:chartreuse">
 Get me(get ME):
</span>

```bash
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTMwNTM1LCJleHAiOjE2NDkyMTY5MzV9.DFo665vJsdi2E2OK_CoYknoAlEIcprfHrQ6vt4ZsqjpfpAedLm05ey9QZB5hkyhV7YmfsdqZK6kCwWZqdqMfKg'
```

It will return something like:

```bash
{
    "id": 1,
    "username": "hihi118",
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "gender": true,
    "phoneNumber": "0397286905",
    "picture": "https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp",
    "dob": "2022-04-05T10:35:30.6840429",
    "createdAt": "2022-04-05T10:36:07"
}
```

<span style="color:blue">
 Update a User(update user):
</span>

```bash
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTMwNTM1LCJleHAiOjE2NDkyMTY5MzV9.DFo665vJsdi2E2OK_CoYknoAlEIcprfHrQ6vt4ZsqjpfpAedLm05ey9QZB5hkyhV7YmfsdqZK6kCwWZqdqMfKg'
```

It will return something like:

```bash
{
    "id": 1,
    "username": "hihi118",
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "gender": true,
    "phoneNumber": "0397286905",
    "picture": "https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp",
    "dob": "2022-04-05T10:35:30.6840429",
    "createdAt": "2022-04-05T10:36:07"
}
```

