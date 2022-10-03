import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserProfile } from '../interfaces/UserProfile';
import {AuthenticationService} from './authentication.service'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly API_URL = 'http://localhost:8080/User'
  readonly Path_Url = 'http://localhost:8080'
 requestHeader = new HttpHeaders({
  'No-Auth': 'True', 

})
  constructor(private httpUser:HttpClient,
    private AuthenticationService: AuthenticationService
    ) { }
// ajout user
addUser(user : any){
  return this.httpUser.post(`${this.API_URL}/addUser`,user);
}
// delete
deleteu(id : any){
  return this.httpUser.delete(`${this.API_URL}/deleteUser/${id}`);
}
// update
editu(user : any){
  return this.httpUser.put(`${this.API_URL}/updateUser`,user);
}
// list
showall(){
  return this.httpUser.get(`${this.API_URL}/getUsers`);
}
// get by id
getById(id : any){
  return this.httpUser.get(`${this.API_URL}/getUserById/${id}`);
}

// if u exists
uexists(login : any,pwd : any){
  let queryParams = new HttpParams();
    queryParams = queryParams.append("login",login);
    queryParams = queryParams.append("pwd",pwd);
    return this.httpUser.get(`${this.API_URL}/uexists/`,{params:queryParams});
}

public login(loginData: any) {
  
  return this.httpUser.post(this.Path_Url + '/Auth/authenticate', loginData, {
    headers: this.requestHeader,
  });
}

public roleMatch(allowedRoles: string | any[]): boolean |any {
  let isMatch = false ;
  const userRoles: any = this.AuthenticationService.getRoles();

  if (userRoles != null && userRoles) {
    for (let i = 0; i < userRoles.length; i++) {
      for (let j = 0; j < allowedRoles.length; j++) {
        if (userRoles[i].roleName === allowedRoles[j]) {
          isMatch = true;
          return isMatch;
        } else {
          return isMatch;
        }
      }
    }
  }
}

public  getUserNameJWT(jwt : any)
{
  return this.httpUser.get(this.Path_Url +`/Auth/getUserFLJWT/${jwt}`, {
    responseType: 'text',
  });
}


public getUserJWT(jwt : string ){
  return this.httpUser.get<UserProfile>(this.Path_Url +`/Auth/getUserJWT/${jwt}`)
}

public getAgentJWT(jwt : string ){
  return this.httpUser.get<number>(this.Path_Url +`/Auth/getAgentId/${jwt}`)
}

public getRoleJWT(jwt : string ){
  return this.httpUser.get<number>(this.Path_Url +`/Auth/getUserRoleJWT/${jwt}`)
}
}
