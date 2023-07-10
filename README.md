I write a very simple bank account kata just to satisfy the requirements.\
Only two objects Account and Transaction, one account has many transactions.
- Account has only balances field and list of transactions
- Transaction has operation type, date of operation, amount and balance

One AccountService to realize three user stories: 
- make a deposit
- make a withdrawal
- print the history of all the operations

We can use "mvn test" to test three user stories