import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HospedeComponent } from './hospede.component';
import { HospedeService } from './hospede.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';
import { NzModalService, NzModalModule } from 'ng-zorro-antd/modal';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzTableModule } from 'ng-zorro-antd/table';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Adicione esta linha


describe('HospedeComponent', () => {
  let component: HospedeComponent;
  let fixture: ComponentFixture<HospedeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HospedeComponent],
      imports: [NzModalModule, NzInputModule, NzSelectModule, NzTableModule, FormsModule, BrowserAnimationsModule], 
      providers: [
        HospedeService,
        NzModalService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HospedeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
