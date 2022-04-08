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
    - Using JWT => Authorization: Bearer Token 
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
 @@ Update user(update user): 
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

There are 3 account types: ["PRIMARY", "LOAN", "SAVING"]

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

```diff
 + Get All Transactions(get all transactions):
```

```bash
curl --location --request GET 'http://localhost:8080/accounts/3/transactions' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few'
```

It will return something like:

```bash
HTTP/1.1 200 Get Successfully
[
    {
        "id": 8,
        "account": 3,
        "purpose": "Heheh",
        "amount": 2.00,
        "createdAt": "2022-04-08T21:44:14",
        "plus": false
    },
    {
        "id": 9,
        "account": 3,
        "purpose": "Heheh",
        "amount": 1.00,
        "createdAt": "2022-04-08T21:44:26",
        "plus": false
    }
]
```

# **API Transaction(/transactions/)**

```diff
 + Create Transfer Transaction(transfer):
```

```bash
curl --location --request POST 'http://localhost:8080/transactions/transfer' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few' \
--header 'Content-Type: application/json' \
--data-raw '{
    "from_account": 3,
    "pinCode": "123432",
    "to_account": 5,
    "amount": 2.00,
    "purpose": "Hello bro!"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Transfer Succefully
```

```diff
 ! Create Transfer Transaction(transfer):
```

```bash
curl --location --request POST 'http://localhost:8080/transactions/transfer' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few' \
--header 'Content-Type: application/json' \
--data-raw '{
    "from_account": 3,
    "pinCode": "123432",
    "to_account": 5,
    "amount": 2.00,
    "purpose": "Hello bro!"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Transfer Succefully
```

```diff
 ! Create Loan Transaction(loan):
```

```bash
curl --location --request POST 'http://localhost:8080/transactions/loan' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pinCode": "235478",
    "from_account": 6,
    "amount": 10.00,
    "purpose": "Heheh"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Loan Succefully
{
    "pinCode": null,
    "from_account": 6,
    "to_account": null,
    "amount": 10.00,
    "purpose": "Heheh"
}
```

```diff
 ! Create Withdraw Transaction(withdraw):
```

```bash
curl --location --request POST 'http://localhost:8080/transactions/withdraw' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pinCode": "123432",
    "from_account": 3,
    "amount": 1.00,
    "purpose": "Heheh"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Withdraw Succefully
{
    "pinCode": null,
    "from_account": 3,
    "to_account": null,
    "amount": 1.00,
    "purpose": "Heheh"
}
```

```diff
 ! Get Transaction(get transaction):
```

```bash
curl --location --request GET 'http://localhost:8080/transactions/8' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few'
```

It will return something like:

```bash
HTTP/1.1 200 Withdraw Succefully
{
    "id": 8,
    "from_account": 3,
    "to_account": null,
    "amount": 2.00,
    "purpose": "Heheh",
    "createdAt": "2022-04-08T21:44:14"
}
```

```diff
 ! Get All Transactions(get all transactions):
```

```bash
curl --location --request GET 'http://localhost:8080/transactions/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few'
```

It will return something like:

```bash
HTTP/1.1 200 Withdraw Succefully
[
    {
        "id": 7,
        "from_account": 6,
        "to_account": null,
        "amount": 10.00,
        "purpose": "Heheh",
        "createdAt": "2022-04-08T21:08:11"
    },
    {
        "id": 8,
        "from_account": 3,
        "to_account": null,
        "amount": 2.00,
        "purpose": "Heheh",
        "createdAt": "2022-04-08T21:44:14"
    },
    {
        "id": 9,
        "from_account": 3,
        "to_account": null,
        "amount": 1.00,
        "purpose": "Heheh",
        "createdAt": "2022-04-08T21:44:26"
    }
]
```

# **API Resource(/resources/)**

```diff
 + Upload Resource(upload resource):
```

Upload Form File Path(Example here /D:/OneDrive/Pictures/Hopetoun_falls.jpg)

```bash
curl --location --request POST 'http://localhost:8080/resources/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few' \
--form 'file=@"/D:/OneDrive/Pictures/Hopetoun_falls.jpg"'
```

It will return something like:

```bash
HTTP/1.1 200 Upload Succefully
```

```diff
 + Get Resource(get resource):
```

```bash
curl --location --request GET 'http://localhost:8080/resources/1649002737261_Hopetoun_falls.jpg' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few'
```

It will return something like:

```bash
HTTP/1.1 200 Get Succefully
How to get Image for Test:

curl --location --request GET 'https://hanu-group-banking-system.herokuapp.com//resources/1649002737261_Hopetoun_falls.jpg' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW5nbGUxMjM0NTYiLCJpYXQiOjE2NDk0MDUyMDcsImV4cCI6MTY0OTQ5MTYwN30.ZZGsoh8HsP0RV9BDTJjQmKbXBCk-wXVpUQaCoEmzvfz5UbJ3SWYan2j5RFB_QoAh9j0_Pj_wwuYaChcG3E_Few'
```

# **API Password Reset Token(/resources/)**

```diff
 + Upload Resource(upload resource):
```

Upload Form File Path(Example here /D:/OneDrive/Pictures/Hopetoun_falls.jpg)

```bash
curl --location --request POST 'http://localhost:8080/password-resets/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "hihi118",
    "phoneNumber": "0397286905",
    "email": "1901040195@s.hanu.edu.vn"
}'
```

Send Token to Registered Email

It will return something like:

```bash
HTTP/1.1 200 Create Succefully
{
    "id": 10,
    "token": "DWvC6KfzXi1DMTWQhhsolOP4",
    "user": {
        "id": 1,
        "firstName": "Tran",
        "lastName": "Tung",
        "username": "hihi118",
        "middleName": "Tien",
        "password": "$2a$10$jrRlChcSSZPuOp2KMmsVluX4N4AuDGnOh1ZVr1yT4XuT.OaBp2jNq",
        "role": "USER",
        "dob": null,
        "gender": true,
        "phoneNumber": "0397286905",
        "address": "Hehe",
        "picture": "https://scontent.fhan2-4.fna.fbcdn.net/v/t1.6435-9/142915405_106600208107240_8287176908190349092_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=5hhWT6I03-QAX-dCcVb&_nc_ht=scontent.fhan2-4.fna&oh=00_AT_PkbGy1ZaYxENnDBG0SHYnNb3aZCI6ODS72z6am9vcFg&oe=626E7006",
        "email": "1901040195@s.hanu.edu.vn",
        "createdAt": "2022-04-05T10:36:07",
        "updatedAt": null,
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    },
    "expiryDate": "2022-04-08T22:11:05.8066289"
}
```

Example:
<p align="center">
    <img src="https://scontent.fpnh22-3.fna.fbcdn.net/v/t1.15752-9/277185137_967448830801082_2756343179952101137_n.png?_nc_cat=102&ccb=1-5&_nc_sid=ae9488&_nc_ohc=d97uZf8JaCEAX-CZeV1&_nc_ht=scontent.fpnh22-3.fna&oh=03_AVJlQLd4_bFmsoQqUOyIl2IrwdKi3wfXjO7R9gCyXsSDqg&oe=62741B96"/>
</p>


```diff
 + Reset Password(reset password):
```

Notice: password - New password which you want change

```bash
curl --location --request PUT 'http://localhost:8080/password-resets/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "token": "teeqL4Fc4jfQGo8chbRkE5gp",
    "password": "123457868"
}'
```

It will return something like:

```bash
HTTP/1.1 200 Change Succefully
{
    {
    "id": 1,
    "username": "hihi118",
    "firstName": "Tran",
    "middleName": "Tien",
    "lastName": "Tung",
    "gender": true,
    "phoneNumber": "0397286905",
    "picture": "https://scontent.fhan2-4.fna.fbcdn.net/v/t1.6435-9/142915405_106600208107240_8287176908190349092_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=5hhWT6I03-QAX-dCcVb&_nc_ht=scontent.fhan2-4.fna&oh=00_AT_PkbGy1ZaYxENnDBG0SHYnNb3aZCI6ODS72z6am9vcFg&oe=626E7006",
    "dob": "2022-04-08T21:07:53.4135962",
    "createdAt": "2022-04-05T10:36:07"
}
```