import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Agent } from '../interfaces/agent';
import { Caisse } from '../interfaces/caisse';
import { Client } from '../interfaces/Client';
import { Compte } from '../interfaces/compte';
import { Facture } from '../interfaces/facture';
import { Session } from '../interfaces/session';
import { UserProfile } from '../interfaces/UserProfile';
import {AuthenticationService} from './authentication.service'


@Injectable({
  providedIn: 'root'
})
export class FactureService {
  readonly Path_Url = 'http://localhost:8080'
 requestHeader = new HttpHeaders({
  'No-Auth': 'True', 

})
  constructor(private httpFacture:HttpClient,
    private AuthenticationService: AuthenticationService) { }
    // list
showall(){
  return this.httpFacture.get<Facture[]>(`${this.Path_Url}/Debt/getDebts`);
}

showByFactId(id : number)
{
  return this.httpFacture.get<Facture>(`${this.Path_Url}/Debt/getDebtById/${id}`);
}


showByFactRef(ref: string)
{
  return this.httpFacture.get<Facture[]>(`${this.Path_Url}/Debt/getDebtByRef/${ref}`);
}

showByClientId(id : number)
{
  return this.httpFacture.get<Facture[]>(`${this.Path_Url}/Debt/getDebtByClientId/${id}`);
}

showByClientRef(ref : string)
{
  return this.httpFacture.get<Facture[]>(`${this.Path_Url}/Debt/getDebtByClientRef/${ref}`);
}

showByCompteId(id : number)
{
  return this.httpFacture.get<Facture[]>(`${this.Path_Url}/Debt/getDebtByCompteId/${id}`);
}

showByCompteRef(ref : string)
{
  return this.httpFacture.get<Facture[]>(`${this.Path_Url}/Debt/getDebtByComptetRef/${ref}`);
}



GetClientByFactId(id : number)
{
  return this.httpFacture.get<Client>(`${this.Path_Url}/Debt/getClientId/${id}`);
}

GetComptesByFactId(id : number)
{
  return this.httpFacture.get<Compte[]>(`${this.Path_Url}/Debt/getAccountsId/${id}`);
}
GetAccountById(id : any , idF : any )
{
  return this.httpFacture.get<Compte>(`${this.Path_Url}/Debt/getAccbyId/${id}/${idF}` );
}

GetDebtAccId(id : any)
{
  return this.httpFacture.get<Facture>(`${this.Path_Url}/Debt/getFactByAccId/${id}`);
}

GetCaisseAvail(id : any)
{
  return this.httpFacture.get<Session>(`${this.Path_Url}/Facture/getCaisseAvailIdAg/${id}`);
}

payerFact(id : any , m : any ){
  return this.httpFacture.put<Facture>(`${this.Path_Url}/Debt/PayerFact/${id}/${m}`,null);
}

EncaisPayFact(montant : any , agent : any , moder:any, sessionc:any,idF:any, party:any){
  return this.httpFacture.post(`${this.Path_Url}/Facture/PayFact/${montant}/${agent}/${moder}/${sessionc}/${idF}`,party);

}

getSessions(){
  return this.httpFacture.get<Session[]>(`${this.Path_Url}/Facture/getsessions`);
}

getAgent(id : any ){
return this.httpFacture.get(`${this.Path_Url}/Agent/getname/${id}`, {
  responseType: 'text',
});
}

getAvailAgent(){
  return this.httpFacture.get<Agent[]>(`${this.Path_Url}/Facture/getavilAgent`);

}

getAvailCaisse(){
  return this.httpFacture.get<Caisse[]>(`${this.Path_Url}/Facture/getavilCaisse`);

}

getCaisse(id : any ){
  return this.httpFacture.get<Caisse>(`${this.Path_Url}/Facture/getPCDeskById/${id}`);

}

saveSession( ida: any , mnt : any , s : any , e : any, Caisse : any ){
  return this.httpFacture.post(`${this.Path_Url}/Facture/addPCDS/${ida}/${mnt}/${s}/${e}`, Caisse);

}

getUsers(){
  return this.httpFacture.get<UserProfile[]>(`${this.Path_Url}/UserProfile/getUserProfiles`);

}

getprofile(id : any){
  return this.httpFacture.get(`${this.Path_Url}/UserProfile/getProfileById/${id}`, {
    responseType: 'text',
  });
}

deleteup(id : number )
{
  return this.httpFacture.delete(`${this.Path_Url}/UserProfile/deleteUP/${id}`) 
}
}
