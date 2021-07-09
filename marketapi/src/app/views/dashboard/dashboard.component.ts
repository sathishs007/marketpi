import { Component, OnInit } from '@angular/core';
import { getStyle, hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import {SearchService} from '../service/search.service'
@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  constructor(private searchService:SearchService){}

  isSubmit=false;
  loginErrMsg:string;
  startDate:Date;
  endDate:Date;
  companyCode:string;
  companyName:string;
  
  searchResult:any;
companyList:any;
searchmsg:string;
getsummaryStatics:any;
  ngOnInit(): void {
this.companyList=[];
this.searchService.getcompany().subscribe((companyList:any)=>{
this.companyList=companyList.getCompanyList;
},err=>{
  alert("login failer ::: "+err.error.message);
 // this.loading = false;
  //this.loginErrMsg=err.error.message;
})
  }

  onSubmit(loginForm){
    this.searchmsg="";
    this.isSubmit=true;
    this.loginErrMsg="";
    this.searchResult=[];
     if(loginForm.invalid){
       return
     }
     this.startDate=loginForm.value.startDate;
     let userObj={companyCode:loginForm.value.companyCode,startDate:loginForm.value.startDate,endDate:loginForm.value.endDate}
     this.searchService.searchStock(userObj).subscribe((searchResult:any)=>{
      if(searchResult.getStockList.length==0){
        this.searchmsg="no datd found current search";
      }
       this.searchResult=searchResult.getStockList;
       this.companyName=searchResult.companyName;
       this.companyCode=searchResult.companyCode;
       this.getsummaryStatics=searchResult.summaryStatistics;
    },err=>{
      alert("login failer ::: "+err.error.message);
     // this.loading = false;
      //this.loginErrMsg=err.error.message;
    })
    }
  
}
