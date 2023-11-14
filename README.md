
# Project: Philosophers

## Local Version (`exe3_philosophes_remote`)

The local version of the project is available in the `exe3_philosophes_remote` package. To run the application, execute the `Main.java` class.

## Remote Socket Version (`tp_socket`)

The remote socket version of the project is available in the `tp_socket` package. Follow the steps below to run the server and client:

### Server

```bash
java -cp bin tp_socket.MainServer <<port>> <<output type: console or file>>
```

Example:

```bash
java -cp bin tp_socket.MainServer 12345 console
```

### Client

For example:

```bash
java -cp bin tp_socket.MainClient <<IP Address>> <<port>> <<output type: console or file>>
```

Example:

```bash
java -cp bin tp_socket.MainClient 137.194.141.70 12345 console
```

Note: Replace `<<port>>`, `<<output type: console or file>>`, and `<<IP Address>>` with the appropriate values.

---
