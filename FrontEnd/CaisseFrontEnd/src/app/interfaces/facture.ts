import { OriginConnectionPosition } from "@angular/cdk/overlay";
import { Client } from "./Client";
import { Compte } from "./compte";
import { Organisation } from "./organisation";

export interface Facture {
    deb_id: number ,
    debrefe : String ,
    adresse : String ,
    deb_date : Date ,
    deb_duedt: Date,
    deb_printdt: Date ,
    deb_amountinit : number,
    vow_debtype_libelle : String ,
    deb_remb : boolean,
    run_name: String,
    red_name:String,
    par_ID : Client ,
    org_ID : Organisation,
    aco_ID : Compte,
    deb_AMOUNT_CASH : number ,

}