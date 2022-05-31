import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

const ACT_API = 'http://localhost:8080/api/action/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ActionService {
  username: string;
  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {
    const user = this.tokenStorageService.getUser();
    this.username = user.username;
  }

  encrypt(credentials): Observable<any> {
    return this.http.post(ACT_API + 'encrypt', {
      username: this.username,
      path: credentials.filePath,
      enctype: credentials.type
    }, httpOptions);
  }

  decrypt(credentials): Observable<any> {
    return this.http.post(ACT_API + 'decrypt', {
      username: this.username,
      path: credentials.filePath
    }, httpOptions);
  }
}
