CREATE TABLE evento_participante(
   id BIGINT PRIMARY KEY,
   evento_id BIGINT NOT NULL,
   participante_id BIGINT NOT NULL,
   presenca_confirmada BOOLEAN,
   FOREIGN KEY (evento_id) REFERENCES evento(id),
   FOREIGN KEY (participante_id) REFERENCES participante(id)

);