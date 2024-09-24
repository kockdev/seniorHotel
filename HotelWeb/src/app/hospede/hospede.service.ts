import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Hospede {
  id?: number;
  nome: string;
  documento: string;
  telefone: string;
  noHotel: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class HospedeService {
  private apiUrl = 'http://localhost:8080/api/hospedes';

  constructor(private http: HttpClient) {}

  // Método para listar todos os hóspedes
  getHospedes(): Observable<Hospede[]> {
    return this.http.get<Hospede[]>(this.apiUrl);
  }

  // Método para criar um novo hóspede
  createHospede(hospede: Hospede): Observable<Hospede> {
    return this.http.post<Hospede>(this.apiUrl, hospede);
  }

  // Método para atualizar um hóspede
  updateHospede(id: number, hospede: Hospede): Observable<Hospede> {
    return this.http.put<Hospede>(`${this.apiUrl}/${id}`, hospede);
  }

  // Método para deletar um hóspede
  deleteHospede(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  buscarPorNome(nome: string): Observable<Hospede[]> {
    return this.http.get<Hospede[]>(`${this.apiUrl}/buscar/nome?nome=${nome}`);
  }

  // Buscar por documento
  buscarPorDocumento(documento: string): Observable<Hospede[]> {
    return this.http.get<Hospede[]>(`${this.apiUrl}/buscar/documento?documento=${documento}`);
  }

  // Buscar por telefone
  buscarPorTelefone(telefone: string): Observable<Hospede[]> {
    return this.http.get<Hospede[]>(`${this.apiUrl}/buscar/telefone?telefone=${telefone}`);
  }

  // Listar hóspedes no hotel
  listarHospedesNoHotel(): Observable<Hospede[]> {
    return this.http.get<Hospede[]>(`${this.apiUrl}/no-hotel`);
  }

  listarHospedesSemCheckin(): Observable<Hospede[]> {
    return this.http.get<Hospede[]>(`${this.apiUrl}/sem-checkin`);
  }
}
