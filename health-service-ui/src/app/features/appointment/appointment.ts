export interface IAppointment {
  appointmentId: string;
  doctorId: string;
  patientId: string;
  createdBy: string;
  createdTs: string;
  lastUpdatedTs: string;
  appointmentTime: string;
  additionalComment: string;
}
