db.createUser (
    {
        user: "usr-catalog",
        pwd: "pwd-catalog",
        roles: [
            {
                role: "readWrite",
                db: "catalogdb"
            }
        ]
    }
)