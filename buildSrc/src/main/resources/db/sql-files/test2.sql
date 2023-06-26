create table user (
                      id int not null auto_increment,
                      first_name varchar(255),
                      last_name varchar(255),
                      email varchar(255) unique,
                      static_gtid varchar(20),
                      status varchar(10),
                      external boolean,
                      last_login TIMESTAMP,
                      created_on TIMESTAMP,
                      created_by varchar(255),
                      updated_on TIMESTAMP,
                      updated_by varchar(255),
                      version int,
                      PRIMARY KEY (id)
);
create table organisation (
                              id int not null auto_increment,
                              cidb varchar(50) not null,
                              name varchar(255),
                              user_id int,
                              PRIMARY KEY (id)
);