// admin.service.ts - Updated with error handling
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AdminService {
  private baseUrl = 'http://localhost:8080/api/admin'; // Keep your existing URL

  constructor(private http: HttpClient) {}

  registerAdmin(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, data)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      if (error.status === 409) {
        errorMessage = 'Email already exists. Please use a different email.';
      } else if (error.status === 400) {
        errorMessage = 'Invalid input data. Please check your information.';
      } else {
        errorMessage = `Server error: ${error.error?.message || error.statusText || 'Unknown error'}`;
      }
    }
    return throwError(() => new Error(errorMessage));
  }
}