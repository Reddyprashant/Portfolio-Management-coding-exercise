

DROP  TABLE  IF EXISTS portfolio_model;
-- CREATE TABLE portfolio_model (
--       'model_name' varchar (36) NOT NULL,
--       '%_in_equity' INTEGER NOT NULL,
--       '%_in_bonds' INTEGER NOT NULL,
--       '%_in_cash' INTEGER NOT NULL,
--       'min_age' INTEGER NOT NULL,
--       'max_age' INTEGER NOT NULL,
--       PRIMARY KEY ('model_name')
-- )
CREATE TABLE portfolio_model (
      model_name varchar (36) NOT NULL,
      equity INTEGER NOT NULL,
      bonds INTEGER NOT NULL,
      cash INTEGER NOT NULL,
      min_age INTEGER NOT NULL,
      max_age INTEGER NOT NULL,
      PRIMARY KEY (model_name)
);