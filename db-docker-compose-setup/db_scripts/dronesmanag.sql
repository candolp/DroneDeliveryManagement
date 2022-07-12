--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8 (Debian 12.8-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-07-11 14:53:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- DROP DATABASE dronesmanagement;
--
-- TOC entry 3036 (class 1262 OID 24581)
-- Name: dronesmanagement; Type: DATABASE; Schema: -; Owner: dronetestuser
--

CREATE DATABASE dronesmanagement WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


-- ALTER DATABASE dronesmanagement OWNER TO dronetestuser;

\connect dronesmanagement

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 24686)
-- Name: delivery_packages; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.delivery_packages (
                                          id bigint NOT NULL,
                                          delivery_status character varying(255),
                                          time_packages bigint NOT NULL,
                                          delivery_drone_serial_number character varying(255),
                                          order_id bigint
);


ALTER TABLE public.delivery_packages OWNER TO gkspire;

--
-- TOC entry 203 (class 1259 OID 24694)
-- Name: drone; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.drone (
                              serial_number character varying(255) NOT NULL,
                              created bigint,
                              created_by uuid NOT NULL,
                              model integer NOT NULL,
                              name character varying(255),
                              updated bigint,
                              weight_capacity integer NOT NULL
);


ALTER TABLE public.drone OWNER TO gkspire;

--
-- TOC entry 204 (class 1259 OID 24702)
-- Name: drone_location; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.drone_location (
                                       id bigint NOT NULL,
                                       altitude character varying(255),
                                       googlegpslink character varying(255),
                                       last_updated date,
                                       latitude character varying(255),
                                       longitude character varying(255),
                                       drone_status_id bigint
);


ALTER TABLE public.drone_location OWNER TO gkspire;

--
-- TOC entry 205 (class 1259 OID 24710)
-- Name: drone_state_log; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.drone_state_log (
                                        id bigint NOT NULL,
                                        battery_percentage integer NOT NULL,
                                        drone_serial_number character varying(255),
                                        last_update bigint NOT NULL,
                                        state integer
);


ALTER TABLE public.drone_state_log OWNER TO gkspire;

--
-- TOC entry 206 (class 1259 OID 24715)
-- Name: drone_status; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.drone_status (
                                     serial_number character varying(255) NOT NULL,
                                     battery_percentage integer NOT NULL,
                                     state integer,
                                     id bigint
);


ALTER TABLE public.drone_status OWNER TO gkspire;

--
-- TOC entry 211 (class 1259 OID 24752)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: gkspire
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO gkspire;

--
-- TOC entry 207 (class 1259 OID 24720)
-- Name: medication; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.medication (
                                   id bigint NOT NULL,
                                   code character varying(255) NOT NULL,
                                   image character varying(255),
                                   name character varying(255) NOT NULL,
                                   weight bigint NOT NULL
);


ALTER TABLE public.medication OWNER TO gkspire;

--
-- TOC entry 208 (class 1259 OID 24728)
-- Name: order_item; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.order_item (
                                   id bigint NOT NULL,
                                   quantity integer NOT NULL,
                                   medication_id bigint NOT NULL,
                                   orders bigint
);


ALTER TABLE public.order_item OWNER TO gkspire;

--
-- TOC entry 209 (class 1259 OID 24733)
-- Name: orders; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.orders (
                               id bigint NOT NULL,
                               delivered bigint,
                               ordered_by character varying(255),
                               requested bigint,
                               total_weight bigint
);


ALTER TABLE public.orders OWNER TO gkspire;

--
-- TOC entry 210 (class 1259 OID 24738)
-- Name: users; Type: TABLE; Schema: public; Owner: gkspire
--

CREATE TABLE public.users (
                              user_id uuid NOT NULL,
                              email character varying(255),
                              full_name character varying(255),
                              pass_word character varying(255) NOT NULL,
                              username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO gkspire;

--
-- TOC entry 3021 (class 0 OID 24686)
-- Dependencies: 202
-- Data for Name: delivery_packages; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.delivery_packages VALUES (27, 'CREATED', 1657488830399, 'e84e365b-aad8-4e3e-bd32-88e731225286', 22);
INSERT INTO public.delivery_packages VALUES (33, 'CREATED', 1657489310842, 'd425fcde-deaf-474a-a035-3ba371d1834349', 29);
INSERT INTO public.delivery_packages VALUES (66, 'CONFIRMED', 1657490297606, '241bce99-a722-42b2-8a00-3be440559dab', 62);
INSERT INTO public.delivery_packages VALUES (73, 'CREATED', 1657490477264, '241bce99-a722-42b2-8a00-3be440559dab', 70);


--
-- TOC entry 3022 (class 0 OID 24694)
-- Dependencies: 203
-- Data for Name: drone; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.drone VALUES ('83b4135d-0c2e-4a74-bfba-1e2174bd89b9', 1657485450610, '3624e1cd-b968-4cef-a535-251c47b8ff35', 0, 'Sky Beta 350', 1657485450610, 350);
INSERT INTO public.drone VALUES ('7552a9f2-373b-4c29-ad12-c13f0be2baf4', 1657485535985, '3624e1cd-b968-4cef-a535-251c47b8ff35', 0, 'Alpha Cruiser 350', 1657485535985, 350);
INSERT INTO public.drone VALUES ('d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444', 1657485837514, '3624e1cd-b968-4cef-a535-251c47b8ff35', 3, 'Potent runner 400', 1657485837514, 400);
INSERT INTO public.drone VALUES ('e84e365b-aad8-4e3e-bd32-88e731225286', 1657485002310, '3624e1cd-b968-4cef-a535-251c47b8ff35', 0, 'light weight runner 300', 1657488830399, 300);
INSERT INTO public.drone VALUES ('d425fcde-deaf-474a-a035-3ba371d1834349', 1657485581198, '3624e1cd-b968-4cef-a535-251c47b8ff35', 0, 'Heavy Monster 500', 1657489310842, 500);
INSERT INTO public.drone VALUES ('241bce99-a722-42b2-8a00-3be440559dab', 1657485934281, '3624e1cd-b968-4cef-a535-251c47b8ff35', 2, 'Performer 250', 1657490477264, 250);


--
-- TOC entry 3023 (class 0 OID 24702)
-- Dependencies: 204
-- Data for Name: drone_location; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.drone_location VALUES (12, NULL, 'https://goo.gl/maps/LVV1JkMvKPSdKfG7A', NULL, NULL, '-0.2901827', NULL);
INSERT INTO public.drone_location VALUES (14, '20', 'https://goo.gl/maps/LVV1JkMvKPSdKfG7A', NULL, NULL, '-0.2901827', NULL);
INSERT INTO public.drone_location VALUES (16, '20', 'https://goo.gl/maps/LVV1JkMvKPSdKfG7A', NULL, '5.5931685', '-0.2901827', NULL);
INSERT INTO public.drone_location VALUES (17, '20', 'https://goo.gl/maps/LVV1JkMvKPSdKfG7A', NULL, '5.5931685', '-0.2901827', NULL);
INSERT INTO public.drone_location VALUES (19, '56', 'https://goo.gl/maps/LVV1JkMvKPSdKfG7A', NULL, '5.5931685', '-0.2343', NULL);
INSERT INTO public.drone_location VALUES (21, '20', 'https://goo.gl/maps/LVV1JkMvKPSdKfG7A', NULL, '5.5931685', '-0.2901827', NULL);


--
-- TOC entry 3024 (class 0 OID 24710)
-- Dependencies: 205
-- Data for Name: drone_state_log; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.drone_state_log VALUES (11, 80, 'e84e365b-aad8-4e3e-bd32-88e731225286', 1657486575158, 1);
INSERT INTO public.drone_state_log VALUES (13, 80, 'e84e365b-aad8-4e3e-bd32-88e731225286', 1657486625484, 1);
INSERT INTO public.drone_state_log VALUES (15, 80, 'e84e365b-aad8-4e3e-bd32-88e731225286', 1657486651370, 1);
INSERT INTO public.drone_state_log VALUES (18, 60, 'e84e365b-aad8-4e3e-bd32-88e731225286', 1657487451932, 1);
INSERT INTO public.drone_state_log VALUES (20, 80, 'e84e365b-aad8-4e3e-bd32-88e731225286', 1657488745058, 0);
INSERT INTO public.drone_state_log VALUES (28, 80, 'e84e365b-aad8-4e3e-bd32-88e731225286', 1657488830562, 2);
INSERT INTO public.drone_state_log VALUES (34, 100, 'd425fcde-deaf-474a-a035-3ba371d1834349', 1657489310904, 2);
INSERT INTO public.drone_state_log VALUES (67, 100, '241bce99-a722-42b2-8a00-3be440559dab', 1657490297774, 2);
INSERT INTO public.drone_state_log VALUES (68, 100, '241bce99-a722-42b2-8a00-3be440559dab', 1657490370641, 5);
INSERT INTO public.drone_state_log VALUES (69, 100, '241bce99-a722-42b2-8a00-3be440559dab', 1657490468909, 0);
INSERT INTO public.drone_state_log VALUES (74, 100, '241bce99-a722-42b2-8a00-3be440559dab', 1657490477305, 2);


--
-- TOC entry 3025 (class 0 OID 24715)
-- Dependencies: 206
-- Data for Name: drone_status; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.drone_status VALUES ('83b4135d-0c2e-4a74-bfba-1e2174bd89b9', 100, 0, NULL);
INSERT INTO public.drone_status VALUES ('7552a9f2-373b-4c29-ad12-c13f0be2baf4', 100, 0, NULL);
INSERT INTO public.drone_status VALUES ('d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444', 100, 0, NULL);
INSERT INTO public.drone_status VALUES ('e84e365b-aad8-4e3e-bd32-88e731225286', 80, 2, 21);
INSERT INTO public.drone_status VALUES ('d425fcde-deaf-474a-a035-3ba371d1834349', 100, 2, NULL);
INSERT INTO public.drone_status VALUES ('241bce99-a722-42b2-8a00-3be440559dab', 100, 2, NULL);


--
-- TOC entry 3026 (class 0 OID 24720)
-- Dependencies: 207
-- Data for Name: medication; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.medication VALUES (2, 'CLN_625', '2345', 'Clavu-nova_625', 22);
INSERT INTO public.medication VALUES (4, 'PARA', '23rfsdfasdfasdfq4ef3feww3e', 'parecetamol', 25);
INSERT INTO public.medication VALUES (5, 'FER_02', 'dsadfasdfasdfaeweawesd', 'Feroglobin', 12);
INSERT INTO public.medication VALUES (6, 'VAL_1', 'sdfadsfaerergadfafad', 'Valium', 20);
INSERT INTO public.medication VALUES (7, 'VALT_2', 'sdfadsfaerergadfafad', 'Valtrex', 15);
INSERT INTO public.medication VALUES (8, 'ABIL_25', 'sdfadsfaerergadfafad', 'ABILIFY', 25);
INSERT INTO public.medication VALUES (9, 'ADD_15', 'sdfadsfaerergadfafad', 'Adderall', 15);
INSERT INTO public.medication VALUES (10, 'AMI_10', 'sdfadsfaerergadfafad', 'Amitriptyline', 10);


--
-- TOC entry 3027 (class 0 OID 24728)
-- Dependencies: 208
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.order_item VALUES (23, 7, 6, 22);
INSERT INTO public.order_item VALUES (24, 4, 4, 22);
INSERT INTO public.order_item VALUES (25, 3, 5, 22);
INSERT INTO public.order_item VALUES (26, 1, 2, 22);
INSERT INTO public.order_item VALUES (30, 8, 5, 29);
INSERT INTO public.order_item VALUES (31, 10, 9, 29);
INSERT INTO public.order_item VALUES (32, 1, 2, 29);
INSERT INTO public.order_item VALUES (63, 8, 5, 62);
INSERT INTO public.order_item VALUES (64, 1, 2, 62);
INSERT INTO public.order_item VALUES (65, 5, 9, 62);
INSERT INTO public.order_item VALUES (71, 5, 10, 70);
INSERT INTO public.order_item VALUES (72, 8, 7, 70);


--
-- TOC entry 3028 (class 0 OID 24733)
-- Dependencies: 209
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.orders VALUES (22, 0, '3624e1cd-b968-4cef-a535-251c47b8ff35', 1657488830394, 298);
INSERT INTO public.orders VALUES (29, 0, '3624e1cd-b968-4cef-a535-251c47b8ff35', 1657489310835, 268);
INSERT INTO public.orders VALUES (62, 1657490468893, '3624e1cd-b968-4cef-a535-251c47b8ff35', 1657490297602, 193);
INSERT INTO public.orders VALUES (70, 0, '3624e1cd-b968-4cef-a535-251c47b8ff35', 1657490477260, 170);


--
-- TOC entry 3029 (class 0 OID 24738)
-- Dependencies: 210
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: gkspire
--

INSERT INTO public.users VALUES ('3624e1cd-b968-4cef-a535-251c47b8ff35', 'test@mail.com', 'test user', '$2a$10$Cy/cJI3hGDxyHjpGdoGqae6Qm1TZdr6Xps2dlR107uiutIpfpIrT.', 'testuser');
INSERT INTO public.users VALUES ('a062d512-a222-45d6-9e37-3999e9d5cd07', 'new@mail.com', 'New User', '$2a$10$IbY5veC8gY5lND.wU1skAu7FT3s0l9aNo0M7YqeXeLoOmIcmpR3km', 'newUser');
INSERT INTO public.users VALUES ('7fdc1b59-0f0e-47e5-952a-c2770784b4f2', 'candolp@mail.com', 'Candolp test', '$2a$10$7Hjb4RvCMmtsWDEPTiCI.uzsdAGAXITlgeBmaodeK7ak3XTLo80YO', 'candolp');


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 211
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: gkspire
--

SELECT pg_catalog.setval('public.hibernate_sequence', 74, true);


--
-- TOC entry 2865 (class 2606 OID 24693)
-- Name: delivery_packages delivery_packages_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.delivery_packages
    ADD CONSTRAINT delivery_packages_pkey PRIMARY KEY (id);


--
-- TOC entry 2869 (class 2606 OID 24709)
-- Name: drone_location drone_location_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone_location
    ADD CONSTRAINT drone_location_pkey PRIMARY KEY (id);


--
-- TOC entry 2867 (class 2606 OID 24701)
-- Name: drone drone_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone
    ADD CONSTRAINT drone_pkey PRIMARY KEY (serial_number);


--
-- TOC entry 2871 (class 2606 OID 24714)
-- Name: drone_state_log drone_state_log_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone_state_log
    ADD CONSTRAINT drone_state_log_pkey PRIMARY KEY (id);


--
-- TOC entry 2873 (class 2606 OID 24719)
-- Name: drone_status drone_status_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone_status
    ADD CONSTRAINT drone_status_pkey PRIMARY KEY (serial_number);


--
-- TOC entry 2875 (class 2606 OID 24727)
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);


--
-- TOC entry 2881 (class 2606 OID 24732)
-- Name: order_item order_item_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


--
-- TOC entry 2883 (class 2606 OID 24737)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2877 (class 2606 OID 24749)
-- Name: medication uk_nytnj4korv3lou2yohjval6mb; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT uk_nytnj4korv3lou2yohjval6mb UNIQUE (name);


--
-- TOC entry 2879 (class 2606 OID 24747)
-- Name: medication uk_pex4ncvrjs43bnm3oucqghy42; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT uk_pex4ncvrjs43bnm3oucqghy42 UNIQUE (code);


--
-- TOC entry 2885 (class 2606 OID 24751)
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2887 (class 2606 OID 24745)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2890 (class 2606 OID 24764)
-- Name: drone_location fk76xuaa8t17uvlhkv6dwei60cu; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone_location
    ADD CONSTRAINT fk76xuaa8t17uvlhkv6dwei60cu FOREIGN KEY (drone_status_id) REFERENCES public.drone_state_log(id);


--
-- TOC entry 2893 (class 2606 OID 24779)
-- Name: order_item fk7apvagk4obty3ne81kf4o4f2v; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT fk7apvagk4obty3ne81kf4o4f2v FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- TOC entry 2888 (class 2606 OID 24754)
-- Name: delivery_packages fk9m7uvmg6upe689mvtknt9574q; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.delivery_packages
    ADD CONSTRAINT fk9m7uvmg6upe689mvtknt9574q FOREIGN KEY (delivery_drone_serial_number) REFERENCES public.drone(serial_number);


--
-- TOC entry 2891 (class 2606 OID 24769)
-- Name: drone_state_log fkixcskudhu7cs5kmvvn4lqclsb; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone_state_log
    ADD CONSTRAINT fkixcskudhu7cs5kmvvn4lqclsb FOREIGN KEY (drone_serial_number) REFERENCES public.drone(serial_number);


--
-- TOC entry 2894 (class 2606 OID 24784)
-- Name: order_item fkjisa73epwl4dwh3rbo3i3jxpv; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT fkjisa73epwl4dwh3rbo3i3jxpv FOREIGN KEY (orders) REFERENCES public.orders(id);


--
-- TOC entry 2892 (class 2606 OID 24774)
-- Name: drone_status fkl1w7oo7u6nyp4iwhv3o02lm27; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.drone_status
    ADD CONSTRAINT fkl1w7oo7u6nyp4iwhv3o02lm27 FOREIGN KEY (id) REFERENCES public.drone_location(id);


--
-- TOC entry 2889 (class 2606 OID 24759)
-- Name: delivery_packages fkprsbg6kovcj1wyaw8iqyuhrtq; Type: FK CONSTRAINT; Schema: public; Owner: gkspire
--

ALTER TABLE ONLY public.delivery_packages
    ADD CONSTRAINT fkprsbg6kovcj1wyaw8iqyuhrtq FOREIGN KEY (order_id) REFERENCES public.orders(id);


-- Completed on 2022-07-11 14:53:41

--
-- PostgreSQL database dump complete
--

