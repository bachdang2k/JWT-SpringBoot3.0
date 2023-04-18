-- Table: public.role

-- DROP TABLE IF EXISTS public.role;

CREATE TABLE IF NOT EXISTS public.roles
(
    id BIGINT NOT NULL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL
);

INSERT INTO
    public.roles(id, role_name)
VALUES
    ('1', 'ADMIN'),
    ('2', 'USER'),
    ('3', 'AUTHOR');
