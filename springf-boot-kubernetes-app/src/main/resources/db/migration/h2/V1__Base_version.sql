create table bookmarks (
    id bigint default nextval('bo_id_seq') not null,
    title varchar(254) not null,
    url varchar(254) not null,
    created_at timestamp,
    primary key (id)
);
create sequence bo_id_seq start with 1 increment by 50