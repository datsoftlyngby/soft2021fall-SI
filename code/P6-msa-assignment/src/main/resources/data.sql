DROP TABLE IF EXISTS TASKS;
DROP TABLE IF EXISTS TASK;
CREATE TABLE TASK (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      type VARCHAR(50) NOT NULL,
                      title VARCHAR(50) NOT NULL
);
insert into TASK (id, type, title)  VALUES
(1,'Reading Task', 'DDD'),
(2,'Reading Task', 'SOA'),
(3,'Reading Task', 'EIP'),
(4,'Reading Task', 'GraphQL'),
(5,'Mini Project', 'API'),
(6,'Mini Project', 'MSA'),
(7,'Mini Project', 'MOM'),
(8,'Mini Project', 'PA')
