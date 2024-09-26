CREATE TABLE tb_transactions
(
    id UUID NOT NULL PRIMARY KEY,
    amount NUMERIC(38, 2),
    date_time TIMESTAMP(6),
    status VARCHAR(255) CHECK (status IN ('PENDING', 'SUCCESS', 'CANCELED', 'REFUNDED')),

    payer_id BIGINT,
    recipient_id BIGINT,

    CONSTRAINT fk_payer FOREIGN KEY (payer_id) REFERENCES tb_users(id),
    CONSTRAINT fk_recipient FOREIGN KEY (recipient_id) REFERENCES tb_users(id),

    message VARCHAR(255)
);