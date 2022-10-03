import { Compte } from "./compte";

export interface Client{
    par_id : number,
    par_refe : String ,
    par_refe_rxt : String ,
    par_name : String ,
    address: String ,
    par_account : Compte[] ,
}