--liquibase formatted sql

--changeset WowVendorTeamHelper:insert_data


insert into roles (title)
values ('eu-team'),
       ('eu-rv-teav'),
       ('us-rv-team'),
       ('us-dt-team,'),
       ('us-nt1-team'),
       ('us-nt2-team'),
       ('us-nt3-team'),
       ('us-pr-team');


insert into users_privileges (user_id, role_id)
VALUES (1, 1);