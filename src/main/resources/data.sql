INSERT INTO tasks (
    task_title,
    is_done,
    date_added,
    date_done
) VALUES
    ('Do Something', false, DATEADD(SECOND, 0, CURRENT_TIMESTAMP()), NULL),
    ('Do Nothing', true, DATEADD(SECOND, 1, CURRENT_TIMESTAMP()), DATEADD(SECOND, 3, CURRENT_TIMESTAMP())),
    ('Do Anything', false, DATEADD(SECOND, 2, CURRENT_TIMESTAMP()), NULL)
;