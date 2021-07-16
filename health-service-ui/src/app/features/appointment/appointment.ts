export interface IAppointment {
  appointmentId: string;
  doctorId: string;
  doctorName: string;
  patientId: string;
  patientName: string;
  operatorId: string;
  operatorName: string;
  createdTs: string;
  lastUpdatedTs: string;
  appointmentTime: string;
  additionalComment: string;
}
