#include <cstdlib>
#include <iostream>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;

static const cv::String DEFAULT_WIN_NAME = "Default Image";
static const cv::String MODIFIED_WIN_NAME = "Modified Image";

static const cv::String IN_FILENAME = "rectangle.png";
static const cv::String OUT_FILENAME = "rectangle_modified.png";

static void modifyImage(cv::Mat& image)
{
	for (int i = 0; i < image.rows; i++) {
		for (int j = 0; j < image.cols; j++) {
			cv::Vec3b& color = image.at<cv::Vec3b>(i, j); // BGR model

			if ((color[0] == 0) && (color[1] == 0) && (color[2] == 0)) {
				color[0] = 255;
				color[1] = 0;
				color[2] = 0;
			}

			/*cv::Vec3b color = image.at<cv::Vec3b>(cv::Point(iterX, iterY));*/
		}
	}
}

int main(int argc, char* argv[])
{
	cv::Mat image = cv::imread(IN_FILENAME, cv::IMREAD_COLOR);
	if (!image.data) {
		cerr << "error: could not open or find the image" << endl;
		return EXIT_FAILURE;
	}

	namedWindow(DEFAULT_WIN_NAME, cv::WINDOW_AUTOSIZE);
	imshow(DEFAULT_WIN_NAME, image);
	cv::waitKey();

	modifyImage(image);

	namedWindow(MODIFIED_WIN_NAME, cv::WINDOW_AUTOSIZE);
	imshow(MODIFIED_WIN_NAME, image);
	cv::waitKey();

	cv::imwrite(OUT_FILENAME, image);

	return EXIT_SUCCESS;
}