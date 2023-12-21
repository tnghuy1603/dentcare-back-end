create table branch
(
    id                  int identity,
    address      varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    constraint pk_branch primary key (id)
)
    go

create table medication
(
    id          int identity,
    description varchar(255),
    expire_date datetime,
    name        varchar(255),
    price       numeric(38, 2)
    constraint pk_medication primary key (id)
)
    go

create table patient
(
    id           int identity,
    address      varchar(255),
    dob          date,
    gender       varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    oral_health  varchar(255)
    constraint pk_patient primary key (id)
)
    go

create table allergic_drug
(
    patient_id  int not null,
    medicine_id int not null,
    constraint pk_allergic_drug primary key (patient_id, medicine_id)
)
    go

create table prescription
(
    id            int identity,
    created_date  datetime,
    medication_id int,
    patient_id    int,
    quantity      int,
    unit          varchar(255)
)
go

create table tooth
(
    id           int identity,
    tooth_name   varchar(255),
    tooth_number varchar(255)
)
    go

create table tooth_surface
(
    id   varchar(255) not null
        primary key,
    name varchar(255)
)
    go

create table [user]
(
    id           int identity,
    address      varchar(255),
    dob          date,
    email        varchar(255),
    name         varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    role         varchar(255),
    branch_id    int,
    )
    go

create table appointment
(
    id                   int identity
        primary key,
    appointment_date     date,
    appointment_time     time,
    created_at
    note                 varchar(255),
    room                 varchar(255),
    status               varchar(255),
    assistant_dentist_id int,
    dentist_id           int,
    patient_id           int
)
go
create table treatment_plan
(
    id                   int identity
        primary key,
    note                 text,
    start_date           date,
    status               varchar(255),
    assistant_dentist_id int
        constraint FKt1rygn8aa9urgjdea67eoqaa
            references [user],
    dentist_id           int
        constraint FKavoc1my82mnrroyya12hed156
            references [user],
    patient_id           int
        constraint FKqypq6tyg8it7kckoqod93ov8s
            references patient
)
    go

create table invoice
(
    paid_amount       text,
    payment_date      datetime2(6),
    payment_method    varchar(255),
    payment_note      varchar(255),
    treatment_plan_id int not null
        primary key
        constraint FKq4pnpgtbxfp4cx15lyoycyvyq
            references treatment_plan
)
    go

create table treatment
(
    id                int identity
        primary key,
    description       text,
    fee               numeric(38, 2),
    treatment_date    datetime2(6),
    treatment_plan_id int
        constraint FK7hysrsfg9eu72l5swspv3v4ur
            references treatment_plan
)
    go

create table working_schedule
(
    id         int identity
        primary key,
    end_time   datetime2(6),
    start_time datetime2(6)
)
    go

create table dentist_working_schedule
(
    working_schedule_id int not null
        constraint FK2vu1kijeudio47tya6ukhcrke
            references working_schedule,
    dentist_id          int not null
        constraint FKsfkuoja579tck7idgm0k0r3m8
            references [user]
)
    go

