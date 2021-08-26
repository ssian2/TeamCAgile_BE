CREATE TABLE band(
    band_id smallint not null,
    PRIMARY KEY (band_id)
);

CREATE TABLE responsibility(
    responsibility_id smallint not null,
    PRIMARY KEY (responsibility_id)
);

CREATE TABLE capability(
    capability_id smallint PRIMARY KEY NOT NULL,
    capability_name varchar(60) not null,
);

CREATE TABLE employee_role(
    role_id smallint not null,
    role_name varchar(60) not null,
    specification varchar(300),
    spec_doc_url varchar(300) DEFAULT NULL,
    capability_id smallint,
    band_id smallint not null,
    responsibility_id smallint not null,
    PRIMARY KEY (role_id),
    FOREIGN KEY (band_id) REFERENCES band(band_id),
    FOREIGN KEY (capability_id) REFERNECES capability(capability_id),
    FOREIGN KEY (responsibility_id) REFERENCES responsibility(responsibility_id)
);


    