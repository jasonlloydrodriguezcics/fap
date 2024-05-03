CREATE DATABASE StudentDB;

USE StudentDB;

CREATE TABLE StudentDetails (
    student_id INT PRIMARY KEY, student_username VARCHAR(70), progress INT, start_date DATE, end_date DATE, training_id INT, FOREIGN KEY (training_id) REFERENCES TrainingDetails (training_id)
);

CREATE TABLE TrainingDetails (
    training_id INT PRIMARY KEY, training_name VARCHAR(70), trainor_id INT, FOREIGN KEY (trainor_id) REFERENCES TrainorDetails (trainor_id)
);

CREATE TABLE CourseDetails (
    course_id INT PRIMARY KEY, course_name VARCHAR(70), course_description VARCHAR(300), training_id INT, FOREIGN KEY (training_id) REFERENCES TrainingDetails (training_id)
);

CREATE TABLE TrainorDetails (
    trainor_id INT PRIMARY KEY, trainor_username VARCHAR(70)
);

--Add values to the tables

INSERT INTO
    CourseDetails
VALUES (
        1, 'CompTIA A+', 'is the industry standard entry-level certification that proves IT knowledge in networking, security, loT technology, cloud-based storage, hardware and more.', 1
    ),
    (
        2, 'CompTIA Network+', 'certifies a professional-level under- standing of the latest best practices and foundational networking tools and technologies.', 2
    ),
    (
        3, 'CompTIA Security+', 'CompTIA Security+ is a global certification that validates the baseline skills necessary to improve baseline security readiness and incident response.', 3
    ),
    (
        4, 'Network Defense Essentials', 'you will learn the fundamental skills and knowledge required to secure computer networks and defend against cyber attacks.', 4
    ),
    (
        5, 'Ethical Hacking Essentials', 'will teach you the fundamental principles, techniques, and tools used in ethical hacking to identify and mitigate security risks in your organization.', 5
    ),
    (
        6, 'Introduction to SQL', 'building your fundamentals in SQL', 6
    ),
    (
        7, 'Introduction to MySQL Development and Administration', 'further you knowledge in MySQL', 7
    );

INSERT INTO
    TrainingDetails
VALUES (1, 'CompTIA A+', 1),
    (2, 'CompTIA Network+', 1),
    (3, 'CompTIA Security+', 1),
    (
        4, 'Network Defense Essentials', 2
    ),
    (
        5, 'Ethical Hacking Essentials', 2
    ),
    (6, 'Introduction to SQL', 3),
    (
        7, 'Introduction to MySQL Development and Administration', 3
    );

INSERT INTO
    StudentDetails
VALUES (
        1, 'student1', 50, '2024-01-01', '2024-06-01', 1
    ),
    (
        2, 'student2', 30, '2024-02-01', '2024-07-01', 2
    ),
    (
        3, 'student3', 70, '2024-03-01', '2024-08-01', 3
    ),
    (
        4, 'student4', 60, '2024-04-01', '2024-09-01', 4
    ),
    (
        5, 'student5', 90, '2024-05-01', '2024-10-01', 5
    );

INSERT INTO
    TrainorDetails
VALUES (1, 'trainor1'),
    (2, 'trainor2'),
    (3, 'trainor3');

SELECT *
FROM
    CourseDetails
    JOIN TrainingDetails ON CourseDetails.training_id = TrainingDetails.training_id WHERE CourseDetails.training_id = 1;

SELECT *
FROM
    TrainingDetails
    JOIN TrainorDetails ON TrainingDetails.trainor_id = TrainorDetails.trainor_id;

SELECT *
FROM
    StudentDetails
    JOIN TrainingDetails ON StudentDetails.training_id = TrainingDetails.training_id
    JOIN TrainorDetails ON TrainingDetails.trainor_id = TrainorDetails.trainor_id WHERE student_username = 'student1';