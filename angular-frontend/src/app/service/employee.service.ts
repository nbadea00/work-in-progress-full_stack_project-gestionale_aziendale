import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../interface/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  public getUserInfo(temaLeadList: number[]): Observable<User[]>{
    return this.http.post<User[]>(`${environment.URL}/employees/findBySetIdCredentials?dim=${temaLeadList.length}`, temaLeadList);
  }

  public getEmployees(dim: number, page: number): Observable<User[]>{
    return this.http.get<User[]>(`${environment.URL}/employees?dim=${dim}&page=${page}`);
  }

  public getUserInfoByToken(): Observable<User>{
    return this.http.get<User>(`${environment.URL}/employees/token`);
  }
}
