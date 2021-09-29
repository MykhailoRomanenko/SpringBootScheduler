CREATE TABLE IF NOT EXISTS public.professors
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(250) NOT NULL,
    position VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.programs
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(250) NOT NULL,
    faculty VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.courses
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(250) NOT NULL,
    year       INT          NOT NULL,
    program_id UUID
);

ALTER TABLE public.courses
    ADD FOREIGN KEY (program_id)
        REFERENCES public.programs(id);

CREATE TABLE IF NOT EXISTS public.classes
(
    id           UUID PRIMARY KEY,
    class_type   VARCHAR(250) NOT NULL,
    professor_id UUID,
    course_id    UUID
);

ALTER TABLE public.classes
    ADD FOREIGN KEY (professor_id)
        REFERENCES public.professors(id);

ALTER TABLE public.classes
    ADD FOREIGN KEY (course_id)
        REFERENCES public.courses(id);


CREATE TABLE IF NOT EXISTS public.schedule_records
(
    id       UUID PRIMARY KEY,
    room     VARCHAR(250),
    timeslot VARCHAR(250) NOT NULL,
    weeks    VARCHAR(250) NOT NULL,
    day      INT          NOT NULL,
    class_id UUID
);

ALTER TABLE public.schedule_records
    ADD FOREIGN KEY (class_id)
        REFERENCES public.classes(id);