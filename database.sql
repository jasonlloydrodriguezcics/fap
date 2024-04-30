-- Create USER_INFO table
CREATE TABLE USER_INFO (
    USERNAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    ROLE VARCHAR(5) CHECK (ROLE IN ('Admin', 'Guest')),
    IS_ENCRYPTED BOOLEAN,
    PRIMARY KEY (USERNAME)
);

-- Insert admin accounts
INSERT INTO USER_INFO (USERNAME, PASSWORD, ROLE, IS_ENCRYPTED) VALUES 
('admin1', '123', 'Admin', false),
('admin2', 'adminpass', 'Admin', false),
('admin3', 'admin123', 'Admin', false),
('admin4', 'password123', 'Admin', false),
('admin5', 'securepass', 'Admin', false);

-- Insert guest accounts
INSERT INTO USER_INFO (USERNAME, PASSWORD, ROLE, IS_ENCRYPTED) VALUES 
('guest1', '123', 'Guest', false),
('guest2', 'guestpass', 'Guest', false),
('guest3', 'guest123', 'Guest', false),
('guest4', 'passwordguest', 'Guest', false),
('guest5', 'secureguest', 'Guest', false),
('guest6', 'guestuser', 'Guest', false),
('guest7', 'guest2024', 'Guest', false),
('guest8', 'guest2023', 'Guest', false),
('guest9', 'testguest', 'Guest', false),
('guest10', 'guesttest', 'Guest', false),
('guest11', '123guest', 'Guest', false),
('guest12', 'guestpassword', 'Guest', false),
('guest13', 'secure2024', 'Guest', false),
('guest14', 'guestguest', 'Guest', false),
('guest15', 'password2024', 'Guest', false),
('guest16', 'test2024', 'Guest', false),
('guest17', '2024guest', 'Guest', false),
('guest18', 'guest2024test', 'Guest', false),
('guest19', '2024password', 'Guest', false),
('guest20', 'testguest2024', 'Guest', false),
('guest21', 'passwordtest', 'Guest', false),
('guest22', '2024testguest', 'Guest', false),
('guest23', 'guestpassword2024', 'Guest', false),
('guest24', '2024secure', 'Guest', false),
('guest25', 'passwordguest2024', 'Guest', false),
('guest26', 'guest2024password', 'Guest', false),
('guest27', 'secureguest2024', 'Guest', false),
('guest28', 'guest2024secure', 'Guest', false),
('guest29', 'test2024guest', 'Guest', false),
('guest30', 'guesttest2024', 'Guest', false),
('guest31', '2024guesttest', 'Guest', false),
('guest32', 'guest2024test', 'Guest', false),
('guest33', 'password2024guest', 'Guest', false),
('guest34', 'guestpassword2024', 'Guest', false),
('guest35', 'secureguest2024', 'Guest', false),
('guest36', 'guest2024secure', 'Guest', false),
('guest37', 'test2024guest', 'Guest', false),
('guest38', 'guesttest2024', 'Guest', false),
('guest39', '2024guesttest', 'Guest', false),
('guest40', 'guest2024test', 'Guest', false);