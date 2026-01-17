BEGIN;

CREATE TABLE IF NOT EXISTS public.task
(
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    user_id uuid NOT NULL,
    "create" timestamp with time zone NOT NULL,
    update timestamp with time zone NOT NULL,
    comment character varying(400),
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS public.user
(
    id uuid NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.task
    ADD FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

END;