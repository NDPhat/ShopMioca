--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

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
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- Name: accounts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.accounts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.accounts_id_seq OWNER TO postgres;

--
-- Name: accounts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.accounts_id_seq OWNED BY public.accounts.id;


--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    image text
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categories_id_seq OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- Name: cc_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cc_transactions (
    id integer NOT NULL,
    order_id integer NOT NULL,
    transdate timestamp with time zone,
    amount numeric NOT NULL,
    status text
);


ALTER TABLE public.cc_transactions OWNER TO postgres;

--
-- Name: cc_transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cc_transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cc_transactions_id_seq OWNER TO postgres;

--
-- Name: cc_transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cc_transactions_id_seq OWNED BY public.cc_transactions.id;


--
-- Name: colors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.colors (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.colors OWNER TO postgres;

--
-- Name: colors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.colors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.colors_id_seq OWNER TO postgres;

--
-- Name: colors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.colors_id_seq OWNED BY public.colors.id;


--
-- Name: order_products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_products (
    id integer NOT NULL,
    order_id integer,
    name character varying(255) NOT NULL,
    description text,
    price numeric NOT NULL,
    quantity integer NOT NULL,
    subtotal numeric NOT NULL,
    colorname character varying(255),
    image text
);


ALTER TABLE public.order_products OWNER TO postgres;

--
-- Name: order_products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_products_id_seq OWNER TO postgres;

--
-- Name: order_products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_products_id_seq OWNED BY public.order_products.id;


--
-- Name: product_statuses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_statuses (
    id boolean NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.product_statuses OWNER TO postgres;

--
-- Name: product_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_tags (
    product_id integer NOT NULL,
    tag_id integer NOT NULL
);


ALTER TABLE public.product_tags OWNER TO postgres;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description text,
    category_id integer NOT NULL,
    product_status_id boolean DEFAULT false,
    regular_price numeric DEFAULT 0,
    discount_price numeric DEFAULT 0,
    quantity integer DEFAULT 9999,
    image text,
    discount_percent integer,
    information text,
    publish boolean DEFAULT false,
    adddate timestamp with time zone
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_colors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products_colors (
    product_id integer NOT NULL,
    color_id integer NOT NULL
);


ALTER TABLE public.products_colors OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
    id integer NOT NULL,
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    comment text,
    rating integer
);


ALTER TABLE public.reviews OWNER TO postgres;

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reviews_id_seq OWNER TO postgres;

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: sales_orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales_orders (
    id integer NOT NULL,
    order_date date NOT NULL,
    total numeric NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.sales_orders OWNER TO postgres;

--
-- Name: sales_orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sales_orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sales_orders_id_seq OWNER TO postgres;

--
-- Name: sales_orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sales_orders_id_seq OWNED BY public.sales_orders.id;


--
-- Name: tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tags (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.tags OWNER TO postgres;

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tags_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tags_id_seq OWNER TO postgres;

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tags_id_seq OWNED BY public.tags.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    active boolean DEFAULT false,
    acc_id integer,
    address text,
    image text,
    banned boolean DEFAULT false,
    phone character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: accounts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts ALTER COLUMN id SET DEFAULT nextval('public.accounts_id_seq'::regclass);


--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- Name: cc_transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cc_transactions ALTER COLUMN id SET DEFAULT nextval('public.cc_transactions_id_seq'::regclass);


--
-- Name: colors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colors ALTER COLUMN id SET DEFAULT nextval('public.colors_id_seq'::regclass);


--
-- Name: order_products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_products ALTER COLUMN id SET DEFAULT nextval('public.order_products_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: sales_orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales_orders ALTER COLUMN id SET DEFAULT nextval('public.sales_orders_id_seq'::regclass);


--
-- Name: tags id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags ALTER COLUMN id SET DEFAULT nextval('public.tags_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accounts (id, username, password, role_id) FROM stdin;
1	thanhtrung	1	1
8	hieudan1241	123	1
9	hieudan1412312	123	1
11	hieudan15121	01627798269Aa	1
12	hieudan4523698	123456789	1
2	hieudan	123456789	1
15	hieudan5544	123456789	1
16	hieudan125	123456789	1
17	hieudan15123	123456789	1
18	daiphat111	12345678	1
19	hieudan12512315123	123456789	1
20	hieudan1254125	123456789	1
21	hieudan4125123	123456789	1
22	hieudan1451251	123456789	1
23	hieudan51256124	123456789	1
24	hieudan5125626	123456789	1
25	hieudan15333	123456789	1
26	hieudan1231254	123456789	1
27	hieudan1242151231223	123456789	1
28	hieudan1523125	123456789	1
29	hieudan25125125	123456789	1
30	hieudan124124124	123456789	1
31	hieudan4124124	123456789	1
32	hieudan5125123	123456789	1
6	hieudan123	123456789	1
33	daiphat123	12345678	1
34	hieudan56985624587	123456789	1
35	hieudan465465454	123456789	1
36	hieudan465465454512	123456789	1
37	hieudan541251	123456789	1
38	hieudan1541251231251	123456789	2
10	hieudanadmin	123456789	2
40	linda124123123	123456789	1
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (id, name, image) FROM stdin;
1	jewelry	\N
2	pottery	\N
3	fabric	\N
4	Vidu	\N
5	Vidu1	\N
6	Đồ gốm	data:image/png;base64,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
0	Đồ Nhựa	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=f9c14058-79b7-4e49-aa50-459dbc2d88e6
\.


--
-- Data for Name: cc_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cc_transactions (id, order_id, transdate, amount, status) FROM stdin;
4	4	2021-12-01 19:36:15.007+07	1399	Processing
5	5	2021-12-01 19:38:05.427+07	4000	Processing
6	6	2021-12-02 13:25:03.738+07	12398	Canceled
2	2	2021-11-29 16:59:07.212+07	1000	Complete
3	3	2021-11-29 16:59:59.809+07	3999	Pending
7	9	2021-12-04 11:10:04.979+07	7000	Pending
8	10	2021-12-04 11:16:20.004+07	11000	Pending
\.


--
-- Data for Name: colors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.colors (id, name) FROM stdin;
3	green
2	red
1	yellow
4	blue
5	black
\.


--
-- Data for Name: order_products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_products (id, order_id, name, description, price, quantity, subtotal, colorname, image) FROM stdin;
22	7	Aottery 215	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	3999	1	3999	black	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=4b28f2ed-a802-4d67-88c1-e24d9300e863
23	8	biển	xanh tươi	1000	1	1000	green	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=d0e1d3cb-a062-4a05-a68a-6739312b70c5
13	2	biển	xanh tươi	1000	1	1000	green	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=d0e1d3cb-a062-4a05-a68a-6739312b70c5
14	3	Aottery 215	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	3999	1	3999	black	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=4b28f2ed-a802-4d67-88c1-e24d9300e863
15	4	Nguyễn	dung mua	399	1	399	blue	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/258755997_259475992821057_1753872225422316177_n.png?alt=media&token=8985c04a-ecd9-4d14-9d6a-116caf70dcfb
16	4	biển 21	xanh tươi	1000	1	1000	yellow	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=b857bc97-5c63-4863-a552-fbe0250994b5
17	5	biển 5	xanh tươi	1000	4	4000	black	
18	6	Dan Nguyen 123	dung mua	399	1	399	yellow	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/164005662_286242596421910_8571703427607958080_n.jpg?alt=media&token=6b484856-fd10-497b-a3c6-37613b092b15
19	6	Aottery 215	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	3999	1	3999	black	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=4b28f2ed-a802-4d67-88c1-e24d9300e863
20	6	biển 21	xanh tươi	1000	3	3000	yellow	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=b857bc97-5c63-4863-a552-fbe0250994b5
21	6	biển 21	xanh tươi	1000	5	5000	red	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=b857bc97-5c63-4863-a552-fbe0250994b5
24	9	biển 21	xanh tươi	1000	1	1000	red	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=b857bc97-5c63-4863-a552-fbe0250994b5
25	9	biển 2135	xanh tươi	1000	6	6000	yellow	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=d33adb00-474f-4369-82a9-c34394fb5a92
26	10	biển	xanh tươi	1000	5	5000	green	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=d0e1d3cb-a062-4a05-a68a-6739312b70c5
27	10	biển 5	xanh tươi	1000	6	6000	black	
\.


--
-- Data for Name: product_statuses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_statuses (id, name) FROM stdin;
f	no discount
t	discount
\.


--
-- Data for Name: product_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_tags (product_id, tag_id) FROM stdin;
3	1
4	2
7	3
8	3
9	1
1	1
1	2
2	1
2	2
23	3
24	3
25	2
26	1
27	1
28	1
5	2
29	1
30	1
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, name, description, category_id, product_status_id, regular_price, discount_price, quantity, image, discount_percent, information, publish, adddate) FROM stdin;
6	Pottery 3	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	2	t	2000	1800	0	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	10	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	t	\N
3	Jewelry 3	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	1	f	3000	3000	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	0	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	t	\N
4	Pottery 1	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	2	t	10000	8500	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	15	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	f	\N
2	Jewelry 2	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	1	f	2000	2000	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	0	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	t	\N
1	Jewelry 1	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	1	f	50000	50000	0	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	0	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	t	\N
7	Fabric 1	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	3	t	25000	23750	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	5	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	f	\N
10	Jewelry 4	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	1	f	20000	20000	1000	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	0	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	f	\N
12	Dan Nguyen	dung mua	1	t	9999	999.0	100000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=87ac5dd0-0ff4-4c0c-964a-5252aec1aa12	10	50|100|Cotton|Không|	f	\N
13	Dung 	dung mua	1	t	399	359.0	1000		90	10|10|Cotton|hallo|	f	\N
14	Nguyễn Hiếu Đan	dung mua	6	t	399	360.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=0a262612-1298-4930-929e-714d8446fa81	10	30|100|Cotton|hallo|	f	\N
15	Dan Nguyen	dung mua	1	t	399	360.0	100000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/mediterranean-sea.jpg?alt=media&token=7b70a62f-1346-4cd0-9d8a-1906dcc91380	10	20|1000|Cotton|Không|	f	\N
16	Dan Nguyen 123	dung mua	1	t	399	360.0	100000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/164005662_286242596421910_8571703427607958080_n.jpg?alt=media&token=6b484856-fd10-497b-a3c6-37613b092b15	10	20|100|Cotton|Không|	f	\N
17	Nguyễn	dung mua	1	t	399	360.0	100000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/258755997_259475992821057_1753872225422316177_n.png?alt=media&token=8985c04a-ecd9-4d14-9d6a-116caf70dcfb	10	20|100|Cotton|Không|	f	\N
18	Nguyễn	dung mua	1	t	399	360.0	100000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/258755997_259475992821057_1753872225422316177_n.png?alt=media&token=8985c04a-ecd9-4d14-9d6a-116caf70dcfb	10	20|100|Cotton|Không|	f	\N
19	Dan Nguyen	dung mua	1	f	399	399	100000		0	7|20|Cotton|Không|	f	\N
20	biển	xanh tươi	3	f	1000	1000.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=d0e1d3cb-a062-4a05-a68a-6739312b70c5	0	100cm|200 m x 200m|nước , muối|rộng lắm|	f	\N
21	biển 2	xanh tươi	6	f	1000	1000.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=af3dde94-cd70-46ff-8ab6-4160d42c363b	0	100cm|200 m x 200m|nước , muối|rộng lắm|	f	\N
22	biển 2	xanh tươi	5	f	1000	1000.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=533ee12d-fc8b-4726-a34e-b3c5a93ab4aa	0	100cm|200 m x 200m|nước , muối|rộng lắm|	f	\N
23	Dan Nguyen	xanh tươi	6	t	10000	100.0	1000		99	100cm|200 m x 200m|nước , muối|rộng lắm|	f	\N
24	biển 5	xanh tươi	2	t	1000	450.0	1000		55	100cm|200 m x 200m|nước , muối|rộng lắm|	f	\N
25	biển 21	xanh tươi	1	t	1000	760.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=66b7819d-c807-4f88-8e8d-29ad456cccc9	24	100cm|200 m x 200m|nước , muối|Không|	f	\N
27	biển 21	xanh tươi	6	t	1000	710.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=b857bc97-5c63-4863-a552-fbe0250994b5	29	300cm|200 m x 200m|nước , muối|Không|	f	\N
28	biển 2135	xanh tươi	6	t	1000	710.0	1000	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a.jpg?alt=media&token=d33adb00-474f-4369-82a9-c34394fb5a92	29	300cm|200 m x 200m|nước , muối|Không|	f	\N
8	Fabric 2	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	3	t	10000	9700	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	3	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	t	\N
26	Jewelry 225	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	1	t	2000	1800.0	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	10	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress|	t	\N
5	Aottery 215	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	1	t	3999	40.0	9999	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/skydocx-1618443386249.jpeg?alt=media&token=4b28f2ed-a802-4d67-88c1-e24d9300e863	99	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress|	f	\N
9	Fabric 3	Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed utlo perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.	3	t	6000	4980	9999	https://tse2.mm.bing.net/th?id=OIP.0c3H2y1i0bZ-Jw2TrYm4cQAAAA&pid=Api&P=0&w=300&h=300	17	400 g|10 x 10 x 15 cm|60% cotton, 40% polyester|American heirloom jean shorts pug seitan letterpress	t	\N
29	Dan Nguyen 123	xanh tươi	6	t	399	360.0	100000		10	100cm|200 m x 200m|nước , muối|rộng lắm|	f	\N
30	Đồ Mới	xanh tươi	3	t	399	360.0	1000		10	300cm|200 m x 200m|nước , muối|Không|	f	2021-12-02 18:42:51.598+07
\.


--
-- Data for Name: products_colors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products_colors (product_id, color_id) FROM stdin;
2	1
2	3
3	1
3	2
4	2
4	3
6	1
6	3
7	1
7	2
7	3
8	1
8	2
8	3
9	1
9	2
1	1
1	2
17	4
17	1
18	4
18	1
19	3
20	3
20	4
20	2
21	3
21	2
21	1
22	3
22	5
22	2
22	1
23	3
23	2
24	5
24	1
25	3
25	4
26	2
27	2
27	1
28	5
28	2
28	1
5	5
10	1
12	1
13	1
14	1
15	1
16	1
29	2
30	3
30	1
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reviews (id, user_id, product_id, comment, rating) FROM stdin;
2	2	1	asdfa	4
0	15	7	aaa	2
34	15	1	Dan	1
36	15	1	6	5
41	10	2	2	1
49	7	2	3	4
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
1	user
2	admin
\.


--
-- Data for Name: sales_orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sales_orders (id, order_date, total, user_id) FROM stdin;
2	2021-11-29	1000	2
3	2021-11-29	3999	2
4	2021-12-01	1399	2
5	2021-12-01	4000	2
6	2021-12-02	12398	2
7	2021-12-03	3999	2
8	2021-12-04	1000	13
9	2021-12-04	7000	31
10	2021-12-04	11000	31
\.


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tags (id, name) FROM stdin;
1	best
2	new
3	most
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, first_name, last_name, active, acc_id, address, image, banned, phone) FROM stdin;
11	acv@gmail.com	a	a	f	8	\N	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
7	hieudankaz@gmail.com	a	a	t	15	\N	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
15	19110461@student.hcmute.edu.vn	a	a	f	12	\N	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
10	19110425@student.hcmute.edu.vn	\N	\N	f	18	\N	\N	f	\N
14	hieudankt5123@gmail.com	\N	\N	t	23	\N	\N	f	\N
16	hieudankaz124124@gmail.com	\N	\N	t	25	\N	\N	f	\N
19	hieudank4125123t@gmail.com	\N	\N	t	28	\N	\N	f	\N
20	hieudankaz2151251251@gmail.com	\N	\N	t	29	\N	\N	f	\N
21	hieudank4124124t@gmail.com	\N	\N	t	30	\N	\N	f	\N
25	hieudankt4124123@gmail.com	\N	\N	f	34	\N	\N	\N	\N
22	hieudankt124124@gmail.com	\N	\N	t	31	\N	\N	f	\N
26	hieudankt5124125123@gmail.com	\N	\N	f	35	\N	\N	\N	\N
27	hieudankt512412512341@gmail.com	\N	\N	f	36	\N	\N	\N	\N
28	hieudan4123kt@gmail.com	\N	\N	f	37	\N	\N	\N	\N
29	hieuda412512312541nkt@gmail.com	\N	\N	f	38	\N	\N	\N	\N
8	hieudankt@gmail.com	a	a	f	6	\N	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
2	hieudan@gmail.com	Dan	Nguyen	t	2	451234124123123123123|Ngo Quyen Street|DOng nay|Xuan Loc huyen|Ho chi minh|	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
12	hieudankaz1@gmail.com	a	a	f	9	\N	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
24	daiphatcbl@gmail.com	\N	\N	f	33	\N	\N	f	\N
18	hieudankaz1232113@gmail.com	\N	\N	t	27	\N	\N	f	\N
23	hieudank4124125t@gmail.com	\N	\N	t	32	\N	\N	f	\N
1	thanhtrung@gmail.com	thanh	trung	f	1	\N	https://64.media.tumblr.com/ef49be10cda9a110a7089d115b3bd175/tumblr_plwfvqAxNV1y11klio6_250.png	f	\N
9	dan2@gmail.com	\N	\N	t	17	\N	\N	f	\N
17	hieudank4123t@gmail.com	\N	\N	t	26	\N	\N	t	\N
13	binbin0011k@gmail.com	Nguyễn	Đan	t	10	No info|No info|Quận Thủ Đức|No info|Hồ Chí Minh|	https://firebasestorage.googleapis.com/v0/b/mioca-17b89.appspot.com/o/a1638586056702.jpg?alt=media&token=e672db8e-c27d-44f9-8231-ccb7661af2a7	f	0332427030
31	hamho151@gmail.com	\N	\N	t	40	\N	\N	\N	\N
\.


--
-- Name: accounts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accounts_id_seq', 40, true);


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_id_seq', 3, true);


--
-- Name: cc_transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cc_transactions_id_seq', 8, true);


--
-- Name: colors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.colors_id_seq', 4, true);


--
-- Name: order_products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_products_id_seq', 27, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 30, true);


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reviews_id_seq', 49, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);


--
-- Name: sales_orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_orders_id_seq', 10, true);


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tags_id_seq', 3, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 31, true);


--
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);


--
-- Name: categories pk_categories; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT pk_categories PRIMARY KEY (id);


--
-- Name: cc_transactions pk_cc_transactions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cc_transactions
    ADD CONSTRAINT pk_cc_transactions PRIMARY KEY (id);


--
-- Name: colors pk_colors; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colors
    ADD CONSTRAINT pk_colors PRIMARY KEY (id);


--
-- Name: order_products pk_order_products; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT pk_order_products PRIMARY KEY (id);


--
-- Name: product_statuses pk_product_statuses; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_statuses
    ADD CONSTRAINT pk_product_statuses PRIMARY KEY (id);


--
-- Name: product_tags pk_product_tags; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT pk_product_tags PRIMARY KEY (product_id, tag_id);


--
-- Name: products pk_products; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT pk_products PRIMARY KEY (id);


--
-- Name: products_colors pk_products_colors; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_colors
    ADD CONSTRAINT pk_products_colors PRIMARY KEY (product_id, color_id);


--
-- Name: reviews pk_reviews; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT pk_reviews PRIMARY KEY (id);


--
-- Name: roles pk_roles; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT pk_roles PRIMARY KEY (id);


--
-- Name: sales_orders pk_sales_orders; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales_orders
    ADD CONSTRAINT pk_sales_orders PRIMARY KEY (id);


--
-- Name: tags pk_tags; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT pk_tags PRIMARY KEY (id);


--
-- Name: users pk_users; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);


--
-- Name: users users_acc_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_acc_id_key UNIQUE (acc_id);


--
-- Name: users_email_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX users_email_uindex ON public.users USING btree (email);


--
-- Name: users fk_account_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_account_users FOREIGN KEY (acc_id) REFERENCES public.accounts(id);


--
-- Name: products fk_category_products; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_products FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: products_colors fk_colors_products_colors; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_colors
    ADD CONSTRAINT fk_colors_products_colors FOREIGN KEY (color_id) REFERENCES public.colors(id);


--
-- Name: reviews fk_product_reviews; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fk_product_reviews FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: products fk_product_statuses_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_product_statuses_product FOREIGN KEY (product_status_id) REFERENCES public.product_statuses(id);


--
-- Name: product_tags fk_products_product_tags; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT fk_products_product_tags FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: products_colors fk_products_products_colors; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products_colors
    ADD CONSTRAINT fk_products_products_colors FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: accounts fk_role_accounts; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT fk_role_accounts FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: cc_transactions fk_sales_order_cc_transaction; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cc_transactions
    ADD CONSTRAINT fk_sales_order_cc_transaction FOREIGN KEY (order_id) REFERENCES public.sales_orders(id);


--
-- Name: order_products fk_sales_orders_order_products; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT fk_sales_orders_order_products FOREIGN KEY (order_id) REFERENCES public.sales_orders(id);


--
-- Name: product_tags fk_tags_product_tags; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT fk_tags_product_tags FOREIGN KEY (tag_id) REFERENCES public.tags(id);


--
-- Name: reviews fk_user_reviews; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fk_user_reviews FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: sales_orders fk_user_sales_order; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales_orders
    ADD CONSTRAINT fk_user_sales_order FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

