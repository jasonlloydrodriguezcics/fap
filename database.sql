-- Create USER_INFO table
CREATE TABLE USER_INFO (
    USERNAME VARCHAR(100) NOT NULL, PASSWORD VARCHAR(100) NOT NULL, ROLE VARCHAR(10) CHECK (
        ROLE IN (
            'Admin', 'Guest', 'Student', 'Trainor'
        )
    ), IS_ENCRYPTED BOOLEAN, PRIMARY KEY (USERNAME)
);

-- Insert admin accounts
INSERT INTO
    USER_INFO (
        USERNAME, PASSWORD, ROLE, IS_ENCRYPTED
    )
VALUES (
        'admin1', '123', 'Admin', FALSE
    ),
    (
        'admin2', 'adminpass', 'Admin', FALSE
    ),
    (
        'admin3', 'admin123', 'Admin', FALSE
    ),
    (
        'admin4', 'password123', 'Admin', FALSE
    ),
    (
        'admin5', 'securepass', 'Admin', FALSE
    ),
    (
        'admin6', 'a6', 'Admin', FALSE
    ),
    (
        'admin7', 'a7', 'Admin', FALSE
    ),
    (
        'admin8', 'a8', 'Admin', FALSE
    ),
    (
        'admin9', 'a9', 'Admin', FALSE
    ),
    (
        'admin10', 'a10', 'Admin', FALSE
    );

-- Insert guest accounts
INSERT INTO
    USER_INFO (
        USERNAME, PASSWORD, ROLE, IS_ENCRYPTED
    )
VALUES (
        'guest1', '123', 'Guest', FALSE
    ),
    (
        'guest2', 'guestpass', 'Guest', FALSE
    ),
    (
        'guest3', 'guest123', 'Guest', FALSE
    ),
    (
        'guest4', 'passwordguest', 'Guest', FALSE
    ),
    (
        'guest5', 'secureguest', 'Guest', FALSE
    ),
    (
        'guest6', 'g6', 'Guest', FALSE
    ),
    (
        'guest7', 'g7', 'Guest', FALSE
    ),
    (
        'guest8', 'g8', 'Guest', FALSE
    ),
    (
        'guest9', 'g9', 'Guest', FALSE
    ),
    (
        'guest10', 'g10', 'Guest', FALSE
    );

-- Insert student accounts
INSERT INTO
    USER_INFO (
        USERNAME, PASSWORD, ROLE, IS_ENCRYPTED
    )
VALUES (
        'student1', 's1', 'Student', FALSE
    ),
    (
        'student2', 's2', 'Student', FALSE
    ),
    (
        'student3', 's3', 'Student', FALSE
    ),
    (
        'student4', 's4', 'Student', FALSE
    ),
    (
        'student5', 's5', 'Student', FALSE
    ),
    (
        'student6', 's6', 'Student', FALSE
    ),
    (
        'student7', 's7', 'Student', FALSE
    ),
    (
        'student8', 's8', 'Student', FALSE
    ),
    (
        'student9', 's9', 'Student', FALSE
    ),
    (
        'student10', 's10', 'Student', FALSE
    ),
    (
        'student11', 's11', 'Student', FALSE
    ),
    (
        'student12', 's12', 'Student', FALSE
    ),
    (
        'student13', 's13', 'Student', FALSE
    ),
    (
        'student14', 's14', 'Student', FALSE
    ),
    (
        'student15', 's15', 'Student', FALSE
    ),
    (
        'student16', 's16', 'Student', FALSE
    ),
    (
        'student17', 's17', 'Student', FALSE
    ),
    (
        'student18', 's18', 'Student', FALSE
    ),
    (
        'student19', 's19', 'Student', FALSE
    ),
    (
        'student20', 's20', 'Student', FALSE
    ),
    (
        'student21', 's21', 'Student', FALSE
    ),
    (
        'student22', 's22', 'Student', FALSE
    ),
    (
        'student23', 's23', 'Student', FALSE
    ),
    (
        'student24', 's24', 'Student', FALSE
    ),
    (
        'student25', 's25', 'Student', FALSE
    ),
    (
        'student26', 's26', 'Student', FALSE
    ),
    (
        'student27', 's27', 'Student', FALSE
    ),
    (
        'student28', 's28', 'Student', FALSE
    ),
    (
        'student29', 's29', 'Student', FALSE
    ),
    (
        'student30', 's30', 'Student', FALSE
    );

--Insert trainor accounts
INSERT INTO
    USER_INFO (
        USERNAME, PASSWORD, ROLE, IS_ENCRYPTED
    )
VALUES (
        'trainor1', 't1', 'Trainor', FALSE
    ),
    (
        'trainor2', 't2', 'Trainor', FALSE
    ),
    (
        'trainor3', 't3', 'Trainor', FALSE
    ),
    (
        'trainor4', 't4', 'Trainor', FALSE
    ),
    (
        'trainor5', 't5', 'Trainor', FALSE
    ),
    (
        'trainor6', 't6', 'Trainor', FALSE
    ),
    (
        'trainor7', 't7', 'Trainor', FALSE
    ),
    (
        'trainor8', 't8', 'Trainor', FALSE
    ),
    (
        'trainor9', 't9', 'Trainor', FALSE
    ),
    (
        'trainor10', 't10', 'Trainor', FALSE
    );