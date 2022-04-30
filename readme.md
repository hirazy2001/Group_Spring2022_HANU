# **HANU BANKING SYSTEM**

<p align="center">
    <img src="https://www.paymentscardsandmobile.com/wp-content/uploads/2018/08/Future-of-banking.jpg"/>
</p>
<br>

_**_API Deployed: https://hanu-group-banking-system.herokuapp.com/_**_


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
      
    - Account
      - Length of pinCode: 6

# **API User(/users/)**

[//]: # (<span style="color:yellow">)

[//]: # ( Create a user &#40;sign up&#41;:)

[//]: # (</span>)

```diff
! Create a user(sign up):
```

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
HTTP/1.1 200 Created
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


```diff
+ Get a user (get user) (Require ROLE ADMIN):
```

```bash
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTMwNTM1LCJleHAiOjE2NDkyMTY5MzV9.DFo665vJsdi2E2OK_CoYknoAlEIcprfHrQ6vt4ZsqjpfpAedLm05ey9QZB5hkyhV7YmfsdqZK6kCwWZqdqMfKg'
```

It will return something like:

```bash
HTTP/1.1 200 Get Successfully
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

```diff
+ Get me(get ME):
```

```bash
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTMwNTM1LCJleHAiOjE2NDkyMTY5MzV9.DFo665vJsdi2E2OK_CoYknoAlEIcprfHrQ6vt4ZsqjpfpAedLm05ey9QZB5hkyhV7YmfsdqZK6kCwWZqdqMfKg'
```

It will return something like:

```bash
HTTP/1.1 200 Get Succesffuly
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

```diff
 @@ Update user(update user): @@
```

```bash
curl --location --request PUT 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTY1NzA3LCJleHAiOjE2NDkyNTIxMDd9.fjU9H0wrTq_3fojVNgbLEQenMydCuJLoAoQQSJnJcjTPeXLaC3C8IniW-98TdL0zobSYGmxBf0DnP3L60e41Hg' \
--header 'Content-Type: application/json' \
--data-raw '{
    "picture": "https://scontent.fhan2-4.fna.fbcdn.net/v/t1.6435-9/142915405_106600208107240_8287176908190349092_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=5hhWT6I03-QAX-dCcVb&_nc_ht=scontent.fhan2-4.fna&oh=00_AT_PkbGy1ZaYxENnDBG0SHYnNb3aZCI6ODS72z6am9vcFg&oe=626E7006"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Updated
{
    "id": 1,
    "username": "hihi118",
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "gender": true,
    "phoneNumber": "0397286905",
    "picture": "https://scontent.fhan2-4.fna.fbcdn.net/v/t1.6435-9/142915405_106600208107240_8287176908190349092_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=5hhWT6I03-QAX-dCcVb&_nc_ht=scontent.fhan2-4.fna&oh=00_AT_PkbGy1ZaYxENnDBG0SHYnNb3aZCI6ODS72z6am9vcFg&oe=626E7006",
    "dob": "2022-04-06T16:13:38.1191218",
    "createdAt": "2022-04-05T10:36:07"
}
```


```diff
 + Get All Users(get all users) (Require ROLE ADMIN):
```

```bash
curl --location --request GET 'http://localhost:8080/users/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDkyMzQ5NTEsImV4cCI6MTY0OTMyMTM1MX0.9T1tCYNpq6JbI726KVO3YpGBgeUvc9yeYUAIv1z6djcNA38wIBcNHYGInxqgsa5-YOrYVnWrL9VG6l4j0s4mOw' \
--data-raw ''
```

It will return something like:

```bash
HTTP/1.1 200 Get Succesffuly
[
    {
        "id": 1,
        "username": "hihi118",
        "firstName": "Tran",
        "middleName": "Tien",
        "lastName": "Tung",
        "gender": true,
        "phoneNumber": "0397286905",
        "picture": "https://scontent.fhan2-4.fna.fbcdn.net/v/t1.6435-9/142915405_106600208107240_8287176908190349092_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=5hhWT6I03-QAX-dCcVb&_nc_ht=scontent.fhan2-4.fna&oh=00_AT_PkbGy1ZaYxENnDBG0SHYnNb3aZCI6ODS72z6am9vcFg&oe=626E7006",
        "dob": "2022-04-06T16:13:38.1191218",
        "createdAt": "2022-04-05T10:36:07"
    },
    {
        "id": 4,
        "username": "nangle123456",
        "firstName": "Le",
        "middleName": "Duc",
        "lastName": "Nang",
        "gender": true,
        "phoneNumber": "0397286900",
        "picture": "https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp",
        "dob": "2022-04-06T16:13:38.1191218",
        "createdAt": "2022-04-06T15:48:43"
    }
]
```


```diff
 + Get All Accounts of User(get all users) (Require ROLE ADMIN):
```

```bash
curl --location --request GET 'http://localhost:8080/users/1/accounts' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTY1NzA3LCJleHAiOjE2NDkyNTIxMDd9.fjU9H0wrTq_3fojVNgbLEQenMydCuJLoAoQQSJnJcjTPeXLaC3C8IniW-98TdL0zobSYGmxBf0DnP3L60e41Hg'
```

It will return something like:

```bash
HTTP/1.1 200 Get Succesffuly
[
    {
        "id": 3,
        "uId": 1,
        "balance": 0.00,
        "pinCode": "235478",
        "accountStatus": "hehe",
        "accountType": "PRIMARY",
        "createdAt": "2022-04-05T21:23:28",
        "updatedAt": null
    },
    {
        "id": 5,
        "uId": 1,
        "balance": 0.00,
        "pinCode": "235478",
        "accountStatus": "hehe",
        "accountType": "PRIMARY",
        "createdAt": "2022-04-06T16:56:17",
        "updatedAt": null
    }
]
```

# **API Authentication(/auth/)**

```diff
 + Authentication User(auth user):
 Authentication user and return JSON Web Token for Client
```

```bash
curl --location --request POST 'http://localhost:8080/auth/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "nangle123456",
    "password": "123456"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Authenticatied
{
    "id": 4,
    "type": "Bearer",
    "username": "nangle123456",
    "password": "$2a$10$U22cB3wUcy1PzKy0KDvBI.8tZcLrclkLDJ21RNl0ecRpvOilm9L4K",
    "roles": [
        "ROLE_ADMIN"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDkyMzQ5NTEsImV4cCI6MTY0OTMyMTM1MX0.9T1tCYNpq6JbI726KVO3YpGBgeUvc9yeYUAIv1z6djcNA38wIBcNHYGInxqgsa5-YOrYVnWrL9VG6l4j0s4mOw"
}
```

# **API Account(/accounts/)**

[//]: # (<span style="color:yellow">)

[//]: # ( Create a user &#40;sign up&#41;:)

[//]: # (</span>)

```diff
! Create a account(sign up account):
```

```bash
curl --location --request POST 'http://localhost:8080/accounts/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTY1NzA3LCJleHAiOjE2NDkyNTIxMDd9.fjU9H0wrTq_3fojVNgbLEQenMydCuJLoAoQQSJnJcjTPeXLaC3C8IniW-98TdL0zobSYGmxBf0DnP3L60e41Hg' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userID": 1,
    "accountStatus": "hehe",
    "accountType": "PRIMARY",
    "pinCode": "235478"
}'
```

It will return something like:
```bash
HTTP/1.1 200 Created
...
{
    "id": 5,
    "uId": 1,
    "balance": 0,
    "pinCode": "235478",
    "accountStatus": "hehe",
    "accountType": "PRIMARY",
    "createdAt": "2022-04-06T16:13:38.1191218",
    "updatedAt": null
}
```


```diff
+ Get a account (get account):
```

```bash
curl --location --request GET 'http://localhost:8080/accounts/3' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDkyMzQ5NTEsImV4cCI6MTY0OTMyMTM1MX0.9T1tCYNpq6JbI726KVO3YpGBgeUvc9yeYUAIv1z6djcNA38wIBcNHYGInxqgsa5-YOrYVnWrL9VG6l4j0s4mOw'
```

It will return something like:

```bash
HTTP/1.1 200 Get Successfully
{
    "id": 3,
    "uId": 1,
    "balance": 0.00,
    "pinCode": "235478",
    "accountStatus": "hehe",
    "accountType": "PRIMARY",
    "createdAt": "2022-04-05T21:23:28",
    "updatedAt": null
}
```

```diff
+ Get Balance(get balance):
```

```bash
curl --location --request GET 'http://localhost:8080/accounts/3/balance' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaWhpMTE4IiwiaWF0IjoxNjQ5MTY1NzA3LCJleHAiOjE2NDkyNTIxMDd9.fjU9H0wrTq_3fojVNgbLEQenMydCuJLoAoQQSJnJcjTPeXLaC3C8IniW-98TdL0zobSYGmxBf0DnP3L60e41Hg'
```

It will return something like:

```bash
HTTP/1.1 200 Get Successfully
10.00
```

```diff
 + Get All Accounts(get all accounts) (Require ROLE ADMIN):
```

```bash
curl --location --request GET 'http://localhost:8080/accounts/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDkyMzQ5NTEsImV4cCI6MTY0OTMyMTM1MX0.9T1tCYNpq6JbI726KVO3YpGBgeUvc9yeYUAIv1z6djcNA38wIBcNHYGInxqgsa5-YOrYVnWrL9VG6l4j0s4mOw'
```

It will return something like:

```bash
HTTP/1.1 200 Get Successfully
[
    {
        "id": 3,
        "uId": 1,
        "balance": 0.00,
        "pinCode": "235478",
        "accountStatus": "hehe",
        "accountType": "PRIMARY",
        "createdAt": "2022-04-05T21:23:28",
        "updatedAt": null
    },
    {
        "id": 5,
        "uId": 1,
        "balance": 0.00,
        "pinCode": "235478",
        "accountStatus": "hehe",
        "accountType": "PRIMARY",
        "createdAt": "2022-04-06T16:56:17",
        "updatedAt": null
    }
]
```


```diff
 + Update Account(update account):
```

```bash
curl --location --request PUT 'http://localhost:8080/accounts/3' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDkyMzQ5NTEsImV4cCI6MTY0OTMyMTM1MX0.9T1tCYNpq6JbI726KVO3YpGBgeUvc9yeYUAIv1z6djcNA38wIBcNHYGInxqgsa5-YOrYVnWrL9VG6l4j0s4mOw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pinCode": "123432"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Get Successfully
{
    "id": 3,
    "uId": 1,
    "balance": 0.00,
    "pinCode": "123432",
    "accountStatus": "hehe",
    "accountType": "PRIMARY",
    "createdAt": "2022-04-05T21:23:28",
    "updatedAt": null
}
```