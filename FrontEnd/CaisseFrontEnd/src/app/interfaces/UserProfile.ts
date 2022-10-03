import { Profile } from "./profile";
import { User } from "./user";

export interface UserProfile {
    _id : number,
    DIS_ADMUSER : User,
    dis_ADMPROFILE: Profile[],
    DIS_USP_GRANTED : boolean,
    DIS_USP_STARTDT : Date,
    DIS_USP_ENDDT : Date ,
    login: string,
    PSW : string,
    dis_USE_ID : any


}
