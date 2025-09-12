BEGIN;

DROP TABLE IF EXISTS public.task;
CREATE TABLE IF NOT EXISTS public.task (
id serial PRIMARY KEY,
title character varying(255) NOT NULL,
description character varying(255) NOT NULL,
user_id uuid NOT NULL,
"create" timestamp with time zone NOT NULL,
update timestamp with time zone NOT NULL,
comment TEXT
);

CREATE TABLE public.user_tasks (
id SERIAL PRIMARY KEY,
user_id UUID,
task_id INTEGER,
FOREIGN KEY (task_id) REFERENCES public.task(id) ON DELETE CASCADE
);

COMMIT;
