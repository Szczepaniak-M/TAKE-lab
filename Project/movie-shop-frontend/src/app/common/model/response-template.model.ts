export interface ResponseTemplate<T> {
  status: number,
  payload: T,
  message: string
}
