CREATE TABLE user_documents
(
  id      INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  user_id INT(11)                            NOT NULL,
  name    VARCHAR(40)                        NOT NULL,
  content LONGTEXT
);
CREATE TABLE users
(
  id        INT(11) AUTO_INCREMENT PRIMARY KEY    NOT NULL,
  name      VARCHAR(50)                           NOT NULL,
  create_at TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
  last_name VARCHAR(50)
);
CREATE TABLE users_log
(
  id         INT(11) AUTO_INCREMENT PRIMARY KEY    NOT NULL,
  user_id    INT(11)                               NOT NULL,
  name       VARCHAR(50)                           NOT NULL,
  changed_at TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL
);