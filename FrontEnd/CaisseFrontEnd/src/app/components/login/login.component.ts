import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm: FormGroup | any;
    constructor(private userService: UserService,
      private router : Router , private AuthenticationService : AuthenticationService , 
      public dialog: MatDialog
    ) {    this.loginForm = new FormGroup({
      userName: new FormControl('', [Validators.required,]),
      userPassword: new FormControl('', [Validators.required,])
    });
   } 

  ngOnInit(): void {
    this.AuthenticationService.clear()
  }


  openDialog(enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(DialogAnimationsExampleDialog, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
    });
  }




  login(loginForm :FormGroup ) {
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        console.log("loginnn" , response);
        this.AuthenticationService.setToken(response.jwtToken);
          
          this.userService.getRoleJWT(response.jwtToken).subscribe(
            (resp : any) =>{
              console.log("role", resp)
              if (resp==1){
                this.router.navigate(['/caissier/dashboard']);  
              }
              if (resp==2 || resp==4){
                this.router.navigate(['/chef/dashboard']);  
              }
              if (resp==3){
                this.router.navigate(['/admin/dashboard']);  
              }
              
          
            }
          )
      }
      ,(error : any )=>{
        this.openDialog('0ms', '0ms');
        console.log("feeeeee")

      }
    );
  }
}
@Component({
  selector: 'dialog-animations-example-dialog',
  templateUrl: 'Alert.html',
})
export class DialogAnimationsExampleDialog {
  constructor(public dialogRef: MatDialogRef<DialogAnimationsExampleDialog>) {}
  public closeD(){
    this.dialogRef.close();
  }
}


