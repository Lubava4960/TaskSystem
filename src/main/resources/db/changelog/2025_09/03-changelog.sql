BEGIN;

ALTER TABLE public.task
    ADD COLUMN status VARCHAR(50)  CHECK (status IN ('в ожидании', 'в процессе', 'завершено')) ,
    ADD COLUMN priority VARCHAR(50)  CHECK (priority IN ('высокий', 'средний', 'низкий')) ;
UPDATE public.task
SET status = 'в ожидании', priority = 'средний';


ALTER TABLE public.task
    ALTER COLUMN status SET NOT NULL,
    ALTER COLUMN priority SET NOT NULL;

COMMIT;
