import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import {AuthenticationService} from 'src/app/services/authentication.service'

@Component({
  selector: 'app-cheflayout',
  templateUrl: './cheflayout.component.html',
  styleUrls: ['./cheflayout.component.css']
})
export class CheflayoutComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
 
  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService,
    private router : Router , private AuthenticationService : AuthenticationService) {}

    UserName! : any ;



    public getUser(){
      const jwt=this.AuthenticationService.getToken();
      console.log("jhjjj",jwt);
      return this.userService.getUserNameJWT(jwt).subscribe(
        (response) => {
          
          console.log("uppppp",response);

          this.UserName=response;
          console.log("User" , this.UserName);
        }
      );
      
    }

    ngOnInit(): void {
      this.getUser();
      console.log("User" , this.UserName);
      
    }

    public logout()
    {
      this.AuthenticationService.clear() 
      this.router.navigate(['/home']);
    }
}
