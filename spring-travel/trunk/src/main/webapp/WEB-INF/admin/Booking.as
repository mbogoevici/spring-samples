package

{

	[Bindable]

	[RemoteClass(alias="org.springframework.webflow.samples.booking.Booking")]

	public class Booking

	{

		public var id:int;

		public var checkinDate;

		public var checkoutDate;
	}

}