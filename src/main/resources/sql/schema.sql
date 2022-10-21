drop table if exists tester_bencher;

CREATE TABLE tester_bencher(

                               id bigserial primary key,
                               email varchar(30),
                               pass varchar(30),
                               nickname varchar(20)

);
