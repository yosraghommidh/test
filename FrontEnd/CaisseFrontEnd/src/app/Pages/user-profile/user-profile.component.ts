import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserProfile } from 'src/app/interfaces/UserProfile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserService,
    private router : Router , private AuthenticationService : AuthenticationService
) { }
    userP! : UserProfile ;
    UserName! : any ;
  ngOnInit(): void {
    this.getUserJwt();
    this.getUser();
  }

  public getUser(){
    const jwt=this.AuthenticationService.getToken();
    console.log("jhjjj",jwt);
    return this.userService.getUserNameJWT(jwt).subscribe(
      (response) => {
        

        this.UserName=response;
      }
    );
    
  }
  public getUserJwt(){
    const jwt=this.AuthenticationService.getToken();
    return this.userService.getUserJWT(jwt).subscribe(
      (response) => {
        this.userP=response;
        console.log("UserOOOOOO" , response);

      }
    );
    
  }
}
