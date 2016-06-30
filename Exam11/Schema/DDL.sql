
DROP TABLE script_language;

DROP TABLE scripts;

DROP TABLE languages;



CREATE TABLE languages (
language varchar(255) NOT NULL,
PRIMARY KEY(language)
);



CREATE TABLE scripts(
script_id varchar(255),
description varchar(255),
sourcecode varchar(255),
PRIMARY KEY(script_id)
);



CREATE TABLE script_language(
script_id varchar(255) NOT NULL,
language varchar(255) NOT NULL,
FOREIGN KEY (script_id) references scripts(script_id),
FOREIGN KEY (language) references languages(language)
);


