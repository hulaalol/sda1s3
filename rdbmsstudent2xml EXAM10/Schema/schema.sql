DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS StudyCourse;

CREATE TABLE StudyCourse (
   id INTEGER NOT NULL PRIMARY KEY
  ,name VARCHAR(255) NOT NULL
  ,shortName CHAR(3)
  ,program CHAR(1) /* Bachelor or Master program? */
  ,CHECK (program IN ('B', 'M')) /* Mysql does not really honour CHECK constraints, but pretend it does! */
) ENGINE=InnoDB;


CREATE TABLE Student (
   id INTEGER NOT NULL PRIMARY KEY
  ,fullName VARCHAR(255) NOT NULL
  ,email VARCHAR(255) NOT NULL UNIQUE
  ,studyCourse INTEGER
  ,FOREIGN KEY(studyCourse) REFERENCES StudyCourse(id)
) ENGINE=InnoDB;

-- example data corresponding to university.xml --

INSERT INTO StudyCourse VALUES (1, 'Mobile Media', 'MMB', 'B');
INSERT INTO StudyCourse VALUES (2, 'Computer Science in Media', NULL, 'M');


INSERT INTO Student VALUES(33, 'Jim Bone', 'bone@foo.org', NULL);
INSERT INTO Student VALUES(40, 'Tracy Adams', 'adams@security.com', 2);

-- Selects --

SELECT * FROM Student;
SELECT * FROM StudyCourse;
