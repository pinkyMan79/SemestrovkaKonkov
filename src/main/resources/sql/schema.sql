drop table if exists tester_bencher;
drop table if exists music_files;
drop table if exists messages;

CREATE TABLE tester_bencher
(
    id bigserial not null ,
    email varchar(30),
    pass varchar(30),
    nickname varchar(20)

);

CREATE TABLE music_files(
    f_id bigserial not null ,
    path_to_file varchar(100) not null,
    u_id integer not null,
    m_name varchar(50) not null,

    constraint fk_u_id foreign key (u_id) references tester_bencher(id),
    constraint pk_f_id primary key (f_id)
);

CREATE TABLE messages(
    m_id bigserial not null ,
    m_text varchar(500) not null,
    m_date varchar(30) not null,
    uu_id integer not null,
    sender varchar(20) not null,

    constraint pk_m_id primary key (m_id),
    constraint fk_uu_id foreign key (uu_id) references tester_bencher(id)

)
