/*HEY! HERE'S THE WHOLE DATA*/

/*TABLE CREATION SEQUENCE*/

/*USERS TABLE CREATION*/

create table public.users
(
    id BIGSERIAL not null primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    fullname varchar(255) not null
);
/*NOTES TABLE CREATION*/

CREATE TABLE public.notes(
    id BIGSERIAL not null,
    title varchar(255) not null,
    description varchar(255),
    user_id bigint references public.users
);

/*TABLE DROP SEQUENCE*/

/*NOTES TABLE DROP*/

drop table public.notes;

/*USERS TABLE DROP*/
drop table public.users;

