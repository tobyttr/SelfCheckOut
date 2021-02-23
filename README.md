# Java Self CheckOut POS Web App

This is a Java driven Self CheckOut POS Web App

# Copy this repo

If you copy this repo you must have a PostgresSQL server wich looks like the Schema under. You also have to give credit.

# Schema

## List of relations
| Schema | Name | Type |
| --- | --- | --- |
| public | categories | table |
| public | costumers | table |
| public | costumers_cosid_seq | sequence |
| public | editingproducts | table |
| public | orderlines | table |
| public | orderlines_olineid_seq | sequence |
| public | orders | table |
| public | orders_oid_seq | sequence |
| public | products | table |

## Categories schema
| Column | Type | Nullable |
| --- | --- | --- |
| cid | integers | not null |
| name | text |  |

Indexes: "categories_pkey" PRIMARY KEY, btree (cid)
    
Referenced by: TABLE "products" CONSTRAINT "products_cid_fkey" FOREIGN KEY (cid) REFERENCES categories(cid)


## Costumers schema
| Column | Type | Nullable | Default |
| --- | --- | --- | --- |
| cosid | integers | not null | nextval('costumer_cosid_seq'::regclass) |
| firstname | text |  |  |
| lastname | text |  |  |
| email | text |  |  |
| phone | text |  |  |

Indexes: "costumers_pkey" PRIMARY KEY, btree (cosid)

Referenced by: TABLE "orders" CONSTRAINT "orders_cosid_fkey" FOREIGN KEY (cosid) REFERENCES costumers(cosid)

## Costumers_cosid_seq
| Type | Start | Minimum | Maximum | Increment | Cycles? | Cache |
| --- | --- | --- | --- | --- | --- | --- |
| integer | 1 | 1 | 2147483647 | 1 | no | 1|

Owned by: public.costumers.cosid

## Editingproduct
| Column | Type | Nullable |
| --- | --- | --- |
| plu | text | not null |

Indexes: "editingproduct_pkey" PRIMARY KEY, btree (plu)

## Orderlines
| Column | Type | Nullable | Default |
| --- | --- | --- | -- |
| olineid | integer | not null | nextval('orders_oid_seq'::regclass) |
| oid | integer | not null | |
| prodname | text | not null | |
| prodprice | double precision | not null | |
| totalprice | double precision | not null | |

Indexes: "orderlines_pkey" PRIMARY KEY, btree (olineid)

## Orderlines_olineid_seq
| Type | Start | Minimum | Maximum | Increment | Cycles? | Cache |
| --- | --- | --- | --- | --- | --- | --- |
| integer | 1 | 1 | 2147483647 | 1 | no | 1|

Owned by: public.orderlines.olineid

## Orders
| Column | Type | Nullable | Default |
| --- | --- | --- | -- |
| oid | integer | not null | nextval('orders_oid_seq'::regclass) |
| cosid | integer | not null | |
| total | double precision | not null | |

Indexes: "orders_pkey" PRIMARY KEY, btree (oid)

Foreign-key constraints: "orders_cosid_fkey" FOREIGN KEY (cosid) REFERENCES costumers(cosid)

## Orders_oid_seq
| Type | Start | Minimum | Maximum | Increment | Cycles? | Cache |
| --- | --- | --- | --- | --- | --- | --- |
| integer | 1 | 1 | 2147483647 | 1 | no | 1|

Owned by: public.orders.oid

## Products
| Column | Type | Nullable | Default |
| --- | --- | --- | -- |
| plu | text | not null |
| name | text |  |
| price | double precision |  |
| cid | integer |  | |
| agerestricted | boolean |  |

Indexes: "products_pkey" PRIMARY KEY, btree (plu)

Foreign-key constraints: "products_cid_fkey" FOREIGN KEY (cid) REFERENCES categories(cid)
